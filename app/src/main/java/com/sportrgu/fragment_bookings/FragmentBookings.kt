package com.sportrgu.fragment_bookings

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.sportrgu.R
import com.sportrgu.data.models.people.Booking
import com.sportrgu.data.models.people.StudentModel

class FragmentBookings: Fragment(R.layout.fragment_bookings) {

    private val TAG_FRAGMENT_BOOKINGS = "MyBookingsFragment"

    private lateinit var recyclerView: RecyclerView
    private lateinit var yourBookingsTextView: TextView

    private val bookingsAdapter = BookingsAdapter()

    private val fDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val userReference = fDatabase.getReference("people")

    private val currentUser = FirebaseAuth.getInstance().currentUser
    private var userInfo: StudentModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.id_bookings_recycler)
        yourBookingsTextView = view.findViewById(R.id.id_your_bookings_text)

        setUpBookingsRecycler()
        setUpLoadBookings()
    }

    private fun setUpBookingsRecycler(){
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = bookingsAdapter
    }

    private fun setNewBookingsList(){
        val currentUserInfo = userInfo
        val currentBookings = currentUserInfo?.listOfBookings
        if (currentBookings != null){
            val newList = mutableListOf<Booking>()
            for (booking in currentBookings){
                newList.add(booking.value)
            }
            bookingsAdapter.setNewList(newList)
        }
    }

    private fun setUpLoadBookings(){
        if (currentUser != null){
            val userRef = userReference.child(currentUser.uid)

            userRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    userInfo = snapshot.getValue(StudentModel::class.java)
                    setNewBookingsList()
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d(TAG_FRAGMENT_BOOKINGS, "Бронирования не получены")

                    yourBookingsTextView.text = "Ошибка загрузки бронирований"
                }

            })
        }
    }
}