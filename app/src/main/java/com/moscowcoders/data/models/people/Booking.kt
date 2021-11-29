package com.moscowcoders.data.models.people

data class Booking(
    val userId: String? = null,
    val objectId: String? = null,
    val objectName: String? = null,
    val timeStartString: String? = null,
    val timeStartLong: Long? = null,
    val timeStopString: String? = null,
    val timeStopLong: Long? = null
)