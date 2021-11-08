package com.moscowcoders.ui.checkin

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.firebase.database.*
import com.moscowcoders.R
import com.moscowcoders.data.models.sport_objects.SportObjectModel
import com.moscowcoders.ui.server_listeners.DaysSortHelper

// Класс фрагмента выбора времени и отправки заявки на посещение спортивного объекта

class FragmentCheckIn: Fragment(R.layout.fragment_check_in) {

    // Тег для логов
    private val TAG_FRAGMENT = "FragmentCheckIn"

    // Firebase
    private val fDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val myReference: DatabaseReference = fDatabase.getReference("sport_objects_data")
    private var myObjectRef: DatabaseReference? = null

    private var currentObjectData: SportObjectModel? = null

    private lateinit var id_textview: TextView
    private lateinit var name_textview: TextView
    private lateinit var days_textview: TextView
    private lateinit var bottom_sheet_behavior: BottomSheetBehavior<RecyclerView>
    private lateinit var recyclerview_bottom_sheet: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val id = arguments?.getString("id", "Пустой id")
        myObjectRef = id?.let { myReference.child(it) }

        Log.d(TAG_FRAGMENT, "Аргументы фрагмента: $id}")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        id_textview = view.findViewById(R.id.id_check_in_id)
        name_textview = view.findViewById(R.id.id_check_in_name)
        days_textview = view.findViewById(R.id.id_check_in_days)
        recyclerview_bottom_sheet = view.findViewById(R.id.id_bottom_sheet_recyclerview)
        bottom_sheet_behavior = BottomSheetBehavior.from(recyclerview_bottom_sheet)
        // bottom_sheet_behavior.state = BottomSheetBehavior.STATE_EXPANDED

        recyclerview_bottom_sheet.layoutManager = LinearLayoutManager(requireContext())
        recyclerview_bottom_sheet.adapter = DaysDateAdapter()

        setDataSportObjectsChanged()
    }

    private fun setDataSportObjectsChanged(){
        myObjectRef?.addValueEventListener(MyValueEventListener())
    }

    private inner class MyValueEventListener: ValueEventListener {

        override fun onDataChange(snapshot: DataSnapshot) {
            currentObjectData = snapshot.getValue(SportObjectModel::class.java)
            Log.d(TAG_FRAGMENT, "Ответ: ${currentObjectData}")
            setNewData()
        }

        override fun onCancelled(error: DatabaseError) {
            Log.d(TAG_FRAGMENT, "Код ошибки: ${error.code}. Ошибка: ${error.message}")
        }
    }

    private fun setNewData(){
        id_textview.text = currentObjectData?.id

        val helper = currentObjectData?.days?.let { DaysSortHelper(it) }
        val list = helper?.sortDaysAndPeriods()

        var textForNameTextview = currentObjectData?.name

        if (list != null) {
            for(day in list){
                textForNameTextview += day.toString()
            }
        }
        name_textview.text = textForNameTextview
    }
}