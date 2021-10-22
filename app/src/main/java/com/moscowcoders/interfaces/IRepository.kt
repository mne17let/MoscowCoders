package com.moscowcoders.interfaces

import com.moscowcoders.data.models.sport_objects.SportObjectModel

interface IRepository {
    fun getSportObjects(): List<SportObjectModel>
}