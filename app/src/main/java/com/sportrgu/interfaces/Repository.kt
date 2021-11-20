package com.sportrgu.interfaces

import com.sportrgu.data.models.sport_objects.SportObjectModel

interface Repository {
    fun getSportObjects(): List<SportObjectModel>
}