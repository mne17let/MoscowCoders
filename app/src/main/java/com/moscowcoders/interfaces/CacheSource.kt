package com.moscowcoders.interfaces

import com.moscowcoders.data.models.people.StudentModelRoom

interface CacheSource {
    suspend fun saveNewStudentData(studentModelRoom: StudentModelRoom)

    suspend fun getStudentData(id: String): StudentModelRoom

    suspend fun deleteAll()
}