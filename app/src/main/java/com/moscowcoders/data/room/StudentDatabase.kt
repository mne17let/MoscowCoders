package com.moscowcoders.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.moscowcoders.data.models.people.StudentModel

@Database(entities = [StudentModel::class],version = 1,exportSchema = false)
abstract class StudentDatabase:RoomDatabase() {
    abstract fun studentDao():StudentDao
    companion object{
        @Volatile
        private var INSTANCE: StudentDatabase? =null
        fun getDatabase(context:Context): StudentDatabase{
            return INSTANCE ?: synchronized(this){
                val instanse = databaseBuilder(
                    context.applicationContext,
                    StudentDatabase::class.java,
                    "student_database"

                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instanse
                return instanse
            }
        }
    }
}