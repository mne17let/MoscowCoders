package com.moscowcoders.data.room

import android.app.Application

class StudentApplication : Application() {
    val database: StudentDatabase by lazy { StudentDatabase.getDatabase(this) }
}