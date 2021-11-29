package com.moscowcoders.interfaces

import com.moscowcoders.data.models.people.Booking
import com.moscowcoders.data.models.people.StudentModelRoom

interface Repository {
    suspend fun getStudent(id: String): StudentModelRoom

    suspend fun saveStudent(newStudent: StudentModelRoom)

    suspend fun deleteAllCache()

    suspend fun sendBooking(booking: Booking)

    suspend fun deleteBooking(bookingToDelete: Booking)
}