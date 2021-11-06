package com.moscowcoders.data.room

import androidx.room.*
import com.moscowcoders.data.models.people.StudentModel
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(student: StudentModel)
    @Update
    suspend fun update(student: StudentModel)
    @Delete
    suspend fun delete(student: StudentModel)
    @Query ("SELECT * FROM students WHERE id = :id")
    fun getItem(id: Int): Flow<StudentModel>
    @Query("SELECT * from students ORDER BY name ASC")
    fun getItems(): Flow<List<StudentModel>>


}