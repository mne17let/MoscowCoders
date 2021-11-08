package com.moscowcoders.data.models.sport_objects.ui_format

data class UiDay(
    val dateLong: Long,
    val date: String,
    val listOfPeriods: List<UiPeriod>
)