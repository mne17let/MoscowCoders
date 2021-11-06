package com.moscowcoders.data.models.people

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class StudentModel(
    @PrimaryKey(autoGenerate = true)
     val id: Int = 0,
//    @ColumnInfo(name = "email")
//    val email: String?,
    @ColumnInfo(name = "name")
    val fName: String?,
    @ColumnInfo(name = "lastname")
     val lName: String?,
    @ColumnInfo(name = "gender")
    val gender: String?,
    @ColumnInfo(name = "group")
    val studyGroup: String?,
    @ColumnInfo(name = "studyYear")
    val studyYear: String?,
    @ColumnInfo(name = "faculty")
    val faculty: String?,
    @ColumnInfo(name = "bio")
    val bioOrFavouriteSports: String?
)