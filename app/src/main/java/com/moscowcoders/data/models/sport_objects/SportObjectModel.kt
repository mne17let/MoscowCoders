package com.moscowcoders.data.models.sport_objects

import android.util.Log
import com.moscowcoders.ui.server_listeners.TimeHelper
import java.util.*
import kotlin.collections.HashMap

// Модель спортивного объекта

data class SportObjectModel(
    val id: String? = null,
    val name: String? = null,
    val image_url: String? = null,
    var isOpen: Boolean? = false,
    val days: HashMap<String, HashMap<String, Period>>? = null
) {
    // Тег для логов
    /*private val TAG_MODEL = "ModelTag"

    private val timeHelper = TimeHelper()

    private val daysListSorted = mutableListOf<HashMap<String, Period>>()
    private val stringDataKeys = mutableListOf<String>()
    private val periodsSorted = mutableListOf<Period>()

    private var mapForPrint: SortedMap<Long, String>? = null

    fun mapForList(): Date{
        return timeHelper.getCurrentTime()
    }*/

    /*fun getKeys(){
        val keys = days?.keys

        Log.d(TAG_MODEL, "Ключи: ${keys}")

        Log.d(TAG_MODEL, "Дни: ${days}")

        if (keys != null) {
            mapForPrint = timeHelper.sortMap(keys.toList())
        }

        Log.d(TAG_MODEL, "Отсортированная карта: ${mapForPrint}")

        mapForPrint?.let { logNeccessary(it) }
    }

    private fun logNeccessary(any: Any){
        Log.d(TAG_MODEL, "Отсортированная карта: ${any}")
    }*/
}