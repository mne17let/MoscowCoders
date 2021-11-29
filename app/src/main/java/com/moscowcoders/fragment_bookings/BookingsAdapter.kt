package com.moscowcoders.fragment_bookings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.moscowcoders.R
import com.moscowcoders.data.models.people.Booking

class BookingsAdapter(
    private val onDeleteBookingListener: OnClickDeleteBookingButtonInProfileBookingsListListener
): RecyclerView.Adapter<BookingsAdapter.BookingViewHolder>() {

    private var bookingsList = emptyList<Booking>()

    fun setNewList(list: List<Booking>){
        bookingsList = list
        notifyDataSetChanged()
    }

    fun getListForDeleteItem(): List<Booking>{
        return bookingsList
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookingsAdapter.BookingViewHolder {
        return BookingViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_bookings_list, parent, false),
            onDeleteBookingListener
        )
    }

    override fun onBindViewHolder(holder: BookingsAdapter.BookingViewHolder, position: Int) {
        holder.bind(bookingsList[position])
    }

    override fun getItemCount(): Int {
        return bookingsList.size
    }

    class BookingViewHolder(
        newView: View,
        private val deleteBookingListener: OnClickDeleteBookingButtonInProfileBookingsListListener
        ): RecyclerView.ViewHolder(newView){

        private val textSportObjectName = newView.findViewById<TextView>(R.id.id_item_booking_sport_object_name)
        private val textStartTime = newView.findViewById<TextView>(R.id.id_item_booking_start_time)
        private val deleteBookingButton = newView.findViewById<TextView>(R.id.id_delete_booking_button_in_booking_item)

        fun bind(booking: Booking){
            textSportObjectName.text = booking.objectName
            textStartTime.text = booking.timeStartString

            deleteBookingButton.setOnClickListener {
                deleteBookingListener.onClick(booking)
            }
        }
    }

    interface OnClickDeleteBookingButtonInProfileBookingsListListener{
        fun onClick(bookingForDelete: Booking)
    }
}