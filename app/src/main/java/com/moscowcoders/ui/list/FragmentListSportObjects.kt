package com.moscowcoders.ui.list

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.moscowcoders.MainActivity
import com.moscowcoders.R
import com.moscowcoders.data.models.sport_objects.SportObjectModel
import com.moscowcoders.ui.server_listeners.SportObjectsListListener

// Класс фрагмента со списком спортивных объектов

class FragmentListSportObjects: Fragment(R.layout.fragment_list_sport_objects),
    ListSportObjectsAdapter.OnClickListener {

    // Тег для логов
    private val TAG_FRAGMENT = "FragmentListSportObjects"

    // Firebase
    private val fDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val myReference: DatabaseReference = fDatabase.getReference("sport_objects_data")

    private lateinit var recycler: RecyclerView
    private lateinit var adapter: ListSportObjectsAdapter

    private val list = mutableListOf<SportObjectModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val listOfNames = mutableListOf<String>()
        for(i in list){
            i.name?.let { listOfNames.add(it) }
        }

        Log.d(TAG_FRAGMENT, "Список в onViewCreated: ${listOfNames}")


        recycler = view.findViewById(R.id.id_recyclerview_list_sport_objects)

        setRecyclerView()
        setDataSportObjectsChanged()
    }

    private fun setRecyclerView(){
        recycler.layoutManager = LinearLayoutManager(requireContext())
        adapter = ListSportObjectsAdapter(this)
        recycler.adapter = adapter
    }

    private fun setNewListForAdapter(){
        adapter.setList(list)
    }

    private fun setDataSportObjectsChanged(){
        val listener = SportObjectsListListener()

        myReference.addValueEventListener(listener)

        listener.liveData.observe(viewLifecycleOwner) { newList ->

            val listOfNames = mutableListOf<String>()
            for(i in list){
                i.name?.let { listOfNames.add(it) }
            }

            Log.d(TAG_FRAGMENT, "Старый список после обновления лайвдаты: ${listOfNames}")

            list.clear()

            list.addAll(newList)

            val listOfNewNames = mutableListOf<String>()
            for(i in list){
                i.name?.let { listOfNewNames.add(it) }
            }

            //Log.d(TAG_FRAGMENT, "Новый список после обновления лайвдаты: ${list}")

            setNewListForAdapter()

            Log.d(TAG_FRAGMENT, "Новый список после обновления лайвдаты: ${listOfNewNames}")

        }
    }

    override fun onClick(id: String) {
        Log.d(TAG_FRAGMENT, "Во фрагменте получено: $id")
        (activity as MainActivity).showCheckInOrLoginFragment(id)
    }
}