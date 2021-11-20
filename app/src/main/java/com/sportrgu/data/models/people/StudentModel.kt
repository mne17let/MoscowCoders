package com.sportrgu.data.models.people

data class StudentModel(
    val email: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val gender: String? = null,
    val studyGroup: String? = null,
    val studyYear: String? = null,
    val faculty: String? = null,
    val bioOrFavouriteSports: String? = null,
    val listOfBookings: HashMap<String, Booking>? = null
)