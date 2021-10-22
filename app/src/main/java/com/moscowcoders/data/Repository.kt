package com.moscowcoders.data

import com.moscowcoders.data.models.sport_objects.SportObjectModel
import com.moscowcoders.interfaces.IRepository

class Repository: IRepository {

    override fun getSportObjects(): List<SportObjectModel> {
        return listOf()
    }
}