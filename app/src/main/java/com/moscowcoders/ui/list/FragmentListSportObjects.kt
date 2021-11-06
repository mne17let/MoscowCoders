package com.moscowcoders.ui.list

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.moscowcoders.MainActivity
import com.moscowcoders.R
import com.moscowcoders.data.models.people.StudentModel
import com.moscowcoders.data.models.sport_objects.ui_format.UiSportObject
import com.moscowcoders.ui.authentication.FirebaseAuthenticationHelper
import com.moscowcoders.ui.server_listeners.SportObjectsListListener

// Класс фрагмента со списком спортивных объектов

class FragmentListSportObjects: Fragment(R.layout.fragment_list_sport_objects),
    ListSportObjectsAdapter.OnClickListener {

    // Тег для логов
    private val TAG_FRAGMENT = "FragmentListSportObjects"

    // Firebase Auth
    private val authenticationHelper = FirebaseAuthenticationHelper()
    private var currentUser: FirebaseUser? = null

    // Firebase
    private val fDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val sportObjectsReference: DatabaseReference = fDatabase.getReference("sport_objects_data")
    private val userReference = fDatabase.getReference("people")

    // ProfileData
    private var currentUserProfileData: StudentModel? = null


    private lateinit var recycler: RecyclerView
    private lateinit var adapter: ListSportObjectsAdapter

    // For show load
    private lateinit var progressBar: ProgressBar
    private lateinit var mainContent: LinearLayout

    // ActionBar
    private lateinit var helloTextView: TextView
    private lateinit var buttonProfileSettings: ImageButton

    private val list = mutableListOf<UiSportObject>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val listOfNames = mutableListOf<String>()
        for(i in list){
            i.name.let { listOfNames.add(it) }
        }

        Log.d(TAG_FRAGMENT, "Список в onViewCreated: ${listOfNames}")


        recycler = view.findViewById(R.id.id_recyclerview_list_sport_objects)
        progressBar = view.findViewById(R.id.id_progress_bar_list_sport_objects)
        mainContent = view.findViewById(R.id.id_main_content_fragment_list_sport_objects)
        helloTextView = view.findViewById(R.id.id_textview_hello_name)
        buttonProfileSettings = view.findViewById(R.id.id_button_open_settings)

        setRecyclerView()
        setDataSportObjectsChanged()
        setHelloTextView()
    }

    private fun setRecyclerView(){
        recycler.layoutManager = LinearLayoutManager(requireContext())
        adapter = ListSportObjectsAdapter(this)
        recycler.adapter = adapter
    }

    private fun setNewListForAdapter(){
        adapter.setList(list)
    }

    private fun setHelloTextView(){
        currentUser = authenticationHelper.getCurrentUser()
        val checkUser = currentUser

        if(checkUser != null){
            val profileReference = userReference.child(checkUser.uid)
            val profileListener = FBProfileListener(object : ProfileListenerCallBack{
                override fun onSuccess(data: StudentModel) {
                    currentUserProfileData = data
                    val newHelloText = "Привет, ${data.firstName}"
                    helloTextView.text = newHelloText
                }

                override fun onError() {
                }
            })

            profileReference.addValueEventListener(profileListener)
        } else{
            helloTextView.text = "Привет, незнакомец!"
        }
    }

    private fun setDataSportObjectsChanged(){
        val listener = SportObjectsListListener()

        sportObjectsReference.addValueEventListener(listener)

        listener.liveData.observe(viewLifecycleOwner) { newList ->

            val listOfNames = mutableListOf<String>()
            for(i in list){
                i.name.let { listOfNames.add(it) }
            }

            Log.d(TAG_FRAGMENT, "Старый список после обновления лайвдаты: ${listOfNames}")
            list.clear()
            list.addAll(newList)

            val listOfNewNames = mutableListOf<String>()
            for(i in list){
                i.name.let { listOfNewNames.add(it) }
            }
            //Log.d(TAG_FRAGMENT, "Новый список после обновления лайвдаты: ${list}")
            setNewListForAdapter()
            Log.d(TAG_FRAGMENT, "Новый список после обновления лайвдаты: ${listOfNewNames}")

        }
    }

    private fun showLoad(){
        progressBar.visibility = View.VISIBLE
        mainContent.visibility = View.GONE
    }

    private fun showContent(){
        progressBar.visibility = View.GONE
        mainContent.visibility = View.VISIBLE
    }

    override fun onClick(sportObjectId: String) {
        //showLoad()
        Log.d(TAG_FRAGMENT, "Во фрагменте получено: $sportObjectId")

        val checkCurrentUser = currentUser

        if(checkCurrentUser != null){

            if(currentUserProfileData != null){
                (activity as MainActivity).showCheckInOrLoginFragment(
                    sportObjectId,
                    haveProfile = true,
                    haveAccount = true
                )
            } else{
                Toast.makeText(requireContext(), "Сначала заполните свой профиль в настройках", Toast.LENGTH_SHORT).show()
            }
        } else{
            (activity as MainActivity).showCheckInOrLoginFragment(
                sportObjectId,
                haveProfile = false,
                haveAccount = false
            )
        }
    }

    interface ProfileListenerCallBack{
        fun onSuccess(data: StudentModel)
        fun onError()
    }
}