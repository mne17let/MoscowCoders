package com.sportrgu.fragment_bookings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sportrgu.R
import com.sportrgu.data.models.people.Booking

class BookingsAdapter: RecyclerView.Adapter<BookingsAdapter.BookingViewHolder>() {

    private var bookingsList = emptyList<Booking>()

    fun setNewList(list: List<Booking>){
        bookingsList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookingsAdapter.BookingViewHolder {
        return BookingViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_bookings_list, parent, false))
    }

    override fun onBindViewHolder(holder: BookingsAdapter.BookingViewHolder, position: Int) {
        holder.bind(bookingsList[position])
    }

    override fun getItemCount(): Int {
        return bookingsList.size
    }

    class BookingViewHolder(newView: View): RecyclerView.ViewHolder(newView){
        private val textSportObjectName = newView.findViewById<TextView>(R.id.id_item_booking_sport_object_name)
        private val textStartTime = newView.findViewById<TextView>(R.id.id_item_booking_start_time)

        fun bind(booking: Booking){
            textSportObjectName.text = booking.objectName
            textStartTime.text = booking.timeStartString
        }
    }
}