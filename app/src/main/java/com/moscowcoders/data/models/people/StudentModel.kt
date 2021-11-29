package com.moscowcoders.data.models.people

data class StudentModel(
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
){
    fun mapForRoom(): StudentModelRoom{
        return StudentModelRoom(
            id, email, firstName, lastName, gender, studyGroup, studyYear, faculty, bioOrFavouriteSports, listOfBookings
        )
    }
}