package com.sportrgu.data

import com.sportrgu.data.cloud.CloudSourceImplementation
import com.sportrgu.data.models.people.Booking
import com.sportrgu.data.models.sport_objects.SportObjectModel
import com.sportrgu.interfaces.CloudSource
import com.sportrgu.interfaces.Repository

class RepositoryImplementation: Repository {

    override fun getSportObjects(): List<SportObjectModel> {
        return listOf()
    }

    fun sendBooking(newBooking: Booking){
        CloudSourceImplementation().sendBooking(newBooking)
    }
}