package com.moscowcoders.data.room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moscowcoders.data.models.people.StudentModel
import kotlinx.coroutines.launch

class StudentViewModel(private val studentDao: StudentDao): ViewModel() {
    private fun insertStudent(studentModel: StudentModel){
        viewModelScope.launch{
            studentDao.insert(studentModel)
        }
    }
    private fun getNewItemEntry(
//        email:String,
                                fName:String,
                                lName:String,gender:String,studyGroup:String,
                                studyYear:String,faculty:String,
                                bioOrFavouriteSports:String): StudentModel{
        return StudentModel(
//            email = email,
            fName = fName,
            lName = lName,
            gender = gender,
            studyGroup = studyGroup,
            studyYear = studyYear,
            faculty = faculty,
            bioOrFavouriteSports = bioOrFavouriteSports
        )
    }
    fun addNewItem(fName:String,
                   lName:String,gender:String,studyGroup:String,
                   studyYear:String,faculty:String,
                   bioOrFavouriteSports:String) {
        val newStudent = getNewItemEntry( fName, lName,gender,studyGroup,studyYear,faculty,bioOrFavouriteSports)
        insertStudent(newStudent)
    }
    fun isStudentValid(
//        email:String,
                       fName:String,
                     lName:String,gender:String,studyGroup:String,
                     studyYear:String,faculty:String,
                     bioOrFavouriteSports:String): Boolean {
        if (
//            email.isBlank()
             fName.isBlank() || lName.isBlank() || gender.isBlank() ||
            studyGroup.isBlank() || studyYear.isBlank() || faculty.isBlank() ||
        bioOrFavouriteSports.isBlank()) {
            return false
        }
        return true
    }

}