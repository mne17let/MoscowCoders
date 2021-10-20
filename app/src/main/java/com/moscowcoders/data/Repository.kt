package com.moscowcoders.data

import com.moscowcoders.data.models.SportObjectModel
import com.moscowcoders.interfaces.IRepository

class Repository: IRepository {

    override fun getSportObjects(): List<SportObjectModel> {
        return listOf()
    }
}