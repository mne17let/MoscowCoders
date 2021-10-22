package com.moscowcoders.data.models.sport_objects

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

// Класс для сортировки объектов List, Map и конвертации строк во время и дату и обратно

class TimeHelper {

    fun getCurrentTime(): Date{
        val date: Date = Calendar.getInstance().time
        return date
    }

    fun sortMap(list: List<String>): SortedMap<Long, String>{
        val longSortedMap: SortedMap<Long, String> = getStringLongMap(list)

        val sortedList = mutableListOf<String>()

        return longSortedMap
    }

    private fun getStringLongMap(list: List<String>): SortedMap<Long, String> {
        val newMap = sortedMapOf<Long, String>()

        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.CANADA)

        for (key in list){
            val dateFromString = dateFormat.parse(key)

            if (dateFromString != null){
                newMap[dateFromString.time] = key
            }
        }

        return newMap
    }
}