package com.sportrgu.fragment_sport_objects_list.helpers

import java.text.SimpleDateFormat
import java.util.*

class TimeHelper {

    fun getCurrentTime(): Long {
        val currentTime: Long = Calendar.getInstance().time.time
        return currentTime
    }

    fun parseTimeFromString(string: String): Long{
        val dateFormat = SimpleDateFormat("hh:mm dd-MM-yyyy", Locale.CANADA)

        return dateFormat.parse(string).time
    }

    fun parseStringFromMilliseconds(milliseconds: Long): String{
        val dateFormat = SimpleDateFormat("hh:mm dd-MM-yyyy", Locale.CANADA)
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliseconds
        return calendar.time.toString()
    }

}