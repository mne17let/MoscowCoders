package com.moscowcoders.ui.server_listeners

import java.util.*

class TimeHelper {

    fun getCurrentTime(): Long {
        val currentTime: Long = Calendar.getInstance().time.time
        return currentTime
    }

}