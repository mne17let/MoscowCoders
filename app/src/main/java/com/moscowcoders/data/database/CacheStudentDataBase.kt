package com.moscowcoders.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.moscowcoders.data.models.people.StudentModelRoom

@Database(entities = [StudentModelRoom::class], version = 1, exportSchema = false)
abstract class CacheStudentDataBase: RoomDatabase() {

    abstract fun studentDao(): StudentDao

    companion object{
        @Volatile
        private var INSTANCE: CacheStudentDataBase? = null

        fun getStudentDatabase(context: Context): CacheStudentDataBase{
            val currentInstance = INSTANCE

            val result: CacheStudentDataBase
            if (currentInstance != null){
                result = currentInstance
            } else{
                synchronized(this){
                    val newInstance = Room.databaseBuilder(
                        context.applicationContext,
                        CacheStudentDataBase::class.java,
                        "student_database"
                    ).build()

                    result = newInstance
                }
            }

            return result
        }
    }
}