package com.moscowcoders.data.models

data class SportObjectModel(
    val id: String? = null,
    val name: String? = null,
    val image_url: String? = null,
    var isOpen: Boolean? = false,
    val days: HashMap<String, HashMap<String, Period>>? = null
)