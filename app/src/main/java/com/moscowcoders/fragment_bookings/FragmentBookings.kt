package com.moscowcoders.fragment_bookings

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.moscowcoders.R
import com.moscowcoders.data.models.people.Booking
import com.moscowcoders.data.models.people.StudentModel

class FragmentBookings: Fragment(R.layout.fragment_bookings), BookingsAdapter.OnClickDeleteBookingButtonInProfileBookingsListListener {

    private val TAG_FRAGMENT_BOOKINGS = "MyBookingsFragment"

    private val viewModel: BookingsViewModel = ViewModelProvider(this).get(BookingsViewModel::class.java)

    private lateinit var recyclerView: RecyclerView
    private lateinit var yourBookingsTextView: TextView

    private val bookingsAdapter = BookingsAdapter(this)

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

    override fun onClick(bookingForDelete: Booking) {
        viewModel.deleteBooking(bookingForDelete)

        val currentList = bookingsAdapter.getListForDeleteItem()
        val mutableListOfBookings = mutableListOf<Booking>()
        mutableListOfBookings.addAll(currentList)
        mutableListOfBookings.remove(bookingForDelete)

        bookingsAdapter.setNewList(mutableListOfBookings)
    }
}