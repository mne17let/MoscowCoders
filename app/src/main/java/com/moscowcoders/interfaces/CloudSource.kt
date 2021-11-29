package com.moscowcoders.interfaces

import com.moscowcoders.data.models.people.Booking

interface CloudSource {
    fun sendBooking(newBooking: Booking)

    fun getBooking()

    fun deleteBooking(booking: Booking)
}