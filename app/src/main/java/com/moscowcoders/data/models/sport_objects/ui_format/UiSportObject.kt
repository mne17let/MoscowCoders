package com.moscowcoders.data.models.sport_objects.ui_format

data class UiSportObject(
    val id: String,
    val name: String,
    val image_url: String,
    val days: List<UiDay>
)