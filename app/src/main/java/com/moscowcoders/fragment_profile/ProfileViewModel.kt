package com.moscowcoders.fragment_profile

import android.app.Application
import androidx.lifecycle.*
import com.moscowcoders.data.RepositoryImplementation
import com.moscowcoders.data.models.people.Booking
import com.moscowcoders.data.models.people.StudentModel
import com.moscowcoders.interfaces.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application): AndroidViewModel(application) {

    private val repository: Repository

    val saveStudentProfileLiveData = MutableLiveData<Boolean>()

    init {
        repository = RepositoryImplementation(getApplication())
    }

    fun saveProfileData(studentModel: StudentModel){
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveStudent(studentModel.mapForRoom())
        }
    }

    fun getProfileData(id: String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getStudent(id)
        }
    }

    fun deleteCache(){
        repository
    }

    fun sendBooking(newBooking: Booking){
        viewModelScope.launch(Dispatchers.IO){
            repository.sendBooking(newBooking)
        }
    }

    fun deleteBooking(bookingToDelete: Booking){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteBooking(bookingToDelete)
        }
    }
}