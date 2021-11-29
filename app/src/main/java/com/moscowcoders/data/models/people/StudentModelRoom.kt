package com.moscowcoders.data.models.people

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_table")
data class StudentModelRoom(
    @PrimaryKey
    val id: String? = null,
    val email: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val gender: String? = null,
    val studyGroup: String? = null,
    val studyYear: String? = null,
    val faculty: String? = null,
    val bioOrFavouriteSports: String? = null,
    val listOfBookings: HashMap<String, Booking>? = null
) {
    fun mapForUi(): StudentModel{
        return StudentModel(id, email, firstName, lastName, gender, studyGroup, studyYear, faculty, bioOrFavouriteSports)
    }
}