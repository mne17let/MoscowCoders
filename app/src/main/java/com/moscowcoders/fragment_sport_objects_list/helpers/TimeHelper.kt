package com.moscowcoders.fragment_sport_objects_list.helpers

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
        // val dateFormat = SimpleDateFormat("hh:mm dd-MM-yyyy", Locale.CANADA)
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliseconds
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)

        val stringDateMyFormat: String = "$hour:$minute $day-$month-$year"

        return stringDateMyFormat
    }

}