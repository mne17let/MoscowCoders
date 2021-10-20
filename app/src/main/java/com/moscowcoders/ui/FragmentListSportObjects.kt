package com.moscowcoders.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.moscowcoders.MainActivity
import com.moscowcoders.R
import com.moscowcoders.data.models.SportObjectModel

class FragmentListSportObjects: Fragment(R.layout.fragment_list_sport_objects),
    ListSportObjectsAdapter.OnClickListener {

    private val TAG_FRAGMENT = "FragmentListSportObjects"

    // Firebase
    private val fDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val myReference: DatabaseReference = fDatabase.getReference("sport_objects_data")

    private lateinit var recycler: RecyclerView
    private lateinit var adapter: ListSportObjectsAdapter

    private val list = mutableListOf<SportObjectModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        myReference.addValueEventListener(MyValueEventListener())
    }

    private inner class MyValueEventListener: ValueEventListener{

        override fun onDataChange(snapshot: DataSnapshot) {

            list.clear()

            for (oneSnapshot: DataSnapshot in snapshot.children){
                val newSportObject: SportObjectModel? = oneSnapshot.getValue(SportObjectModel::class.java)

                val isOpen = oneSnapshot.child("isOpen").value

                if (newSportObject != null){
                    newSportObject.isOpen = isOpen as Boolean
                    list.add(newSportObject)
                }

                Log.d(TAG_FRAGMENT, "Ответ: ${oneSnapshot}")

                Log.d(TAG_FRAGMENT, "Мой объект: ${newSportObject}")
            }

            setNewListForAdapter()
        }

        override fun onCancelled(error: DatabaseError) {
            Log.d(TAG_FRAGMENT, "Код ошибки: ${error.code}. Ошибка: ${error.message}")
        }
    }

    override fun onClick(id: String) {
        Log.d(TAG_FRAGMENT, "Во фрагменте получено: $id")
        (activity as MainActivity).showCheckInFragment(id)
    }
}