package com.sportrgu.interfaces

import com.sportrgu.data.models.people.Booking

interface CloudSource {
    fun sendBooking(newBooking: Booking)

    fun getBooking()
}