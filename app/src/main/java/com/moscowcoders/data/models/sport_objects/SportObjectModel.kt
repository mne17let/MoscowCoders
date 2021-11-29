package com.moscowcoders.data.models.sport_objects

import com.moscowcoders.data.models.sport_objects.ui_format.UiSportObject
import com.moscowcoders.fragment_sport_objects_list.helpers.DaysSortHelper
import kotlin.collections.HashMap

// Модель спортивного объекта

data class SportObjectModel(
    val id: String? = null,
    val name: String? = null,
    val image_url: String? = null,
    val days: HashMap<String, HashMap<String, Period>>? = null
) {

    fun mapForUi(): UiSportObject?{
        var result: UiSportObject? = null

        if (days != null && id != null && name != null && image_url != null){
            val timeHelper = DaysSortHelper(days)
            val sortedDaysAndPeriods = timeHelper.sortDaysAndPeriods()

            result = UiSportObject(id, name, image_url, sortedDaysAndPeriods)
        }

        return result
    }

}