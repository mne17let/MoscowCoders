package com.moscowcoders.data.models.people

data class StudentModel(
    private val email: String? = null,
    private val fName: String? = null,
    private val lName: String? = null,
    private val gender: String? = null,
    private val studyGroup: String? = null,
    private val studyYear: String? = null,
    private val faculty: String? = null,
    private val bioOrFavouriteSports: String? = null
)