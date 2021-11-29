package com.moscowcoders.data

import android.content.Context
import com.moscowcoders.data.cloud.CloudSourceImplementation
import com.moscowcoders.data.database.CacheSourceImplementation
import com.moscowcoders.data.database.CacheStudentDataBase
import com.moscowcoders.data.models.people.Booking
import com.moscowcoders.data.models.people.StudentModelRoom
import com.moscowcoders.interfaces.Repository

class RepositoryImplementation(private val context: Context): Repository {

    val cloud = CloudSourceImplementation()

    val dataBaseInstance = CacheStudentDataBase.getStudentDatabase(context)
    val dao = dataBaseInstance.studentDao()
    val cache = CacheSourceImplementation(dao)

    override suspend fun sendBooking(booking: Booking){
        cloud.sendBooking(booking)
    }

    override suspend fun deleteBooking(bookingToDelete: Booking){
        cloud.deleteBooking(bookingToDelete)
    }

    override suspend fun getStudent(id: String): StudentModelRoom {
        return cache.getStudentData(id)
    }

    override suspend fun saveStudent(newStudent: StudentModelRoom) {
        cache.saveNewStudentData(newStudent)
    }

    override suspend fun deleteAllCache() {
        cache.deleteAll()
    }
}