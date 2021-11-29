package com.moscowcoders.fragment_check_in.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.moscowcoders.R
import com.moscowcoders.data.models.people.Booking
import com.moscowcoders.data.models.people.StudentModel
import com.moscowcoders.data.models.sport_objects.SportObjectModel
import com.moscowcoders.data.models.sport_objects.ui_format.UiDay
import com.moscowcoders.data.models.sport_objects.ui_format.UiPeriod
import com.moscowcoders.fragment_sport_objects_list.helpers.DaysSortHelper
import com.moscowcoders.fragment_sport_objects_list.helpers.TimeHelper

// Класс фрагмента выбора времени и отправки заявки на посещение спортивного объекта

class FragmentCheckIn: Fragment(R.layout.fragment_check_in),
    PeriodsCheckInAdapter.OnPeriodClickListener {

    // Тег для логов
    private val TAG_FRAGMENT = "FragmentCheckIn"

    private val viewModel = ViewModelProvider(this).get(CheckInViewModel::class.java)

    private var cacheProfileData: StudentModel? = null

    // Firebase
    private val fDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val myReference: DatabaseReference = fDatabase.getReference("sport_objects_data")
    private var myObjectRef: DatabaseReference? = null

    private var currentObjectData: SportObjectModel? = null

    // Ui
    private lateinit var button_dates_popup_menu: Button
    private lateinit var check_in_button: Button
    private lateinit var textview_current_choice: TextView
    private lateinit var textview_sport_object_title: TextView

    // RecyclerView
    private lateinit var recyclerview_with_periods: RecyclerView
    private lateinit var periodsAdapter: PeriodsCheckInAdapter

    // PopUp menu
    private lateinit var popupMenu: PopupMenu
    private var sortListOfDays: List<UiDay> = emptyList()
    private var clickedDateId: Int? = null

    // Current chosen data
    var currentStringChosenDate: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val id = arguments?.getString("id", "Пустой id")
        myObjectRef = id?.let { myReference.child(it) }

        Log.d(TAG_FRAGMENT, "Аргументы фрагмента: $id}")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        recyclerview_with_periods = view.findViewById(R.id.id_periods_recyclerview)
        button_dates_popup_menu = view.findViewById(R.id.id_popup_with_dates_button)
        check_in_button = view.findViewById(R.id.id_send_check_in_button)
        textview_current_choice = view.findViewById(R.id.id_textview_current_choice)
        textview_sport_object_title = view.findViewById(R.id.id_textview_check_in_into)

        setDaysRecyclerView()
        setDataSportObjectsChanged()
        setPopUpMenu()
        setPopUpDatesButton()
        setCheckInButton()

        setProfileDataObserve()
        getProfileData()
    }

    private fun setProfileDataObserve(){
        viewModel.profileDataLiveData.observe(viewLifecycleOwner){
            cacheProfileData = it
        }
    }

    private fun getProfileData(){
        val currentUserId = FirebaseAuth.getInstance().uid
        if (currentUserId != null){
            viewModel.getProfileData(currentUserId)
        }
    }

    private fun setDaysRecyclerView(){
        periodsAdapter = PeriodsCheckInAdapter(this)
        recyclerview_with_periods.layoutManager = LinearLayoutManager(requireContext())
        recyclerview_with_periods.adapter = periodsAdapter
    }

    private fun setDataSportObjectsChanged(){
        myObjectRef?.addValueEventListener(MyValueEventListener())
    }

    private fun setCheckInButton(){
        check_in_button.setOnClickListener {
            val currentStringChoice = currentStringChosenDate

            val currentCache = cacheProfileData

            if (currentCache == null){
                Toast.makeText(requireContext(), "В кэше ничего не найдено", Toast.LENGTH_SHORT).show()
            } else{

                Toast.makeText(requireContext(), "В кэше найден пользователь: ${currentCache.lastName}", Toast.LENGTH_SHORT).show()

                if (currentStringChoice != null){
                    val timeHelper = TimeHelper()
                    val startLong = timeHelper.parseTimeFromString(currentStringChoice)
                    val stopLong = startLong + 3600000
                    val stopString = timeHelper.parseStringFromMilliseconds(stopLong)
                    viewModel.sendBooking(
                        Booking(
                            currentCache.id,
                            currentObjectData?.id,
                            currentObjectData?.name,
                            currentStringChoice,
                            startLong,
                            stopString,
                            stopLong
                        )
                    )
                } else{
                    Toast.makeText(requireContext(), "Выберите дату и время записи", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setPopUpMenu(){
        popupMenu = PopupMenu(requireContext(), button_dates_popup_menu)
        popupMenu.setOnMenuItemClickListener { menuItem ->
            Toast.makeText(requireContext(), "Нажат итем: ${menuItem.title}, id: ${menuItem.itemId}", Toast.LENGTH_SHORT).show()
            button_dates_popup_menu.text = menuItem.title
            setNewPeriods(menuItem.title.toString())
            resetClickedDate(menuItem.itemId)
            clickedDateId = menuItem.itemId
            false
        }
    }

    private fun setPopUpDatesButton(){
        button_dates_popup_menu.setOnClickListener {
            popupMenu.show()
        }
    }

    private inner class MyValueEventListener: ValueEventListener {

        override fun onDataChange(snapshot: DataSnapshot) {
            currentObjectData = snapshot.getValue(SportObjectModel::class.java)
            Log.d(TAG_FRAGMENT, "Ответ: ${currentObjectData}")
            sortData()
        }

        override fun onCancelled(error: DatabaseError) {
            Log.d(TAG_FRAGMENT, "Код ошибки: ${error.code}. Ошибка: ${error.message}")
        }
    }

    private fun sortData(){
        val currentData = currentObjectData
        val currentDays = currentData?.days

        if (currentDays != null){
            val helper = DaysSortHelper(currentDays)
            val listOfSortedDays = helper.sortDaysAndPeriods()

            setNewPeriods(listOfSortedDays)
            sortListOfDays = listOfSortedDays

            currentData.name?.let { setNewTitle(it) }
        }
    }

    private fun resetClickedDate(id: Int){
        if (clickedDateId != id){
            periodsAdapter.resetClickedItems()
        }
    }

    private fun setNewPeriods(listOfDays: List<UiDay>){
        popupMenu.menu.clear()

        for (i in 0..listOfDays.size - 1){
            popupMenu.menu.add(0, i, i, listOfDays[i].date)
        }
    }

    private fun setNewTitle(name: String){
        val newTitle = "Запись в $name"
        textview_sport_object_title.text = newTitle
    }

    private fun setNewPeriods(stringDate: String){

        val currentSortedDays = sortListOfDays

        var currentPeriods: List<UiPeriod> = emptyList()
        for (day in currentSortedDays){
            if (day.date == stringDate){
                currentPeriods = day.listOfPeriods
            }
        }

        periodsAdapter.setList(currentPeriods)
    }

    override fun onClick(data: UiPeriod) {
        val newText = "Вы выбрали: ${data.open}"
        textview_current_choice.text = newText
        currentStringChosenDate = data.open

        if (textview_current_choice.visibility == View.GONE){
            textview_current_choice.visibility = View.VISIBLE
        }
    }
}