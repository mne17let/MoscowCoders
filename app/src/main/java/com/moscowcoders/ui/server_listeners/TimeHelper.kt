package com.moscowcoders.ui.server_listeners

import android.util.Log
import com.moscowcoders.data.models.sport_objects.Period
import com.moscowcoders.data.models.sport_objects.ui_format.UiDay
import java.text.SimpleDateFormat
import java.util.*

// Класс для сортировки объектов List, Map и конвертации строк во время и дату и обратно

class TimeHelper(private val hashMap: HashMap<String, HashMap<String, Period>>) {

    private fun getCurrentTime(): Date {
        val date: Date = Calendar.getInstance().time
        return date
    }
    //Тег для логов
    private val TAG_TIME_HELPER = "TimeHelperTag"

    fun sortMap() {
        val longStringSortedMap: SortedMap<Long, String> = getStringLongMap()

        val longSortedListOfKeys = longStringSortedMap.keys

        val sortedListOfStringDate = mutableListOf<String>()

        for (key in longSortedListOfKeys) {
            val newValue = longStringSortedMap[key]
            if (newValue != null) {
                sortedListOfStringDate.add(newValue)
            }
        }

        val sortedListOfDays = mutableListOf<UiDay>()

        for (longDate in longStringSortedMap.keys) {
            val newValue = longStringSortedMap[longDate]
            if (newValue != null) {
                sortedListOfDays.add(UiDay(longDate, newValue))
            }
        }

        for(day in sortedListOfDays){
            Log.d(TAG_TIME_HELPER, "День: ${day.date}, дата в мс: ${day.dateLong}")
            Log.d(TAG_TIME_HELPER, "${day.listOfPeriods}")
        }

    }

    private fun getStringLongMap(): SortedMap<Long, String> {
        val newMap = sortedMapOf<Long, String>()

        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.CANADA)

        for (key in hashMap.keys) {
            val dateFromString = dateFormat.parse(key)

            if (dateFromString != null) {
                newMap[dateFromString.time] = key
            }
        }

        return newMap
    }
}