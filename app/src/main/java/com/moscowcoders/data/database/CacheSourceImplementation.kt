package com.moscowcoders.data.database

import com.moscowcoders.data.models.people.StudentModelRoom
import com.moscowcoders.interfaces.CacheSource

class CacheSourceImplementation(private val dao: StudentDao): CacheSource {

    override suspend fun saveNewStudentData(studentModelRoom: StudentModelRoom) {
        dao.addStudent(studentModelRoom)
    }

    override suspend fun getStudentData(id: String): StudentModelRoom {
        return dao.getStudent(id)
    }

    override suspend fun deleteAll() {
        dao.deleteAll()
    }
}