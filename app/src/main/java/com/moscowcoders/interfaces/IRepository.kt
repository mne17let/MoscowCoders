package com.moscowcoders.interfaces

import com.moscowcoders.data.models.SportObjectModel

interface IRepository {
    fun getSportObjects(): List<SportObjectModel>
}