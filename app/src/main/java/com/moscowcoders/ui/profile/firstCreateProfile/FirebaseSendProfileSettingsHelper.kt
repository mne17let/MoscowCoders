package com.moscowcoders.ui.profile.firstCreateProfile

import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.moscowcoders.data.models.people.StudentModel

class FirebaseSendProfileSettingsHelper {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private var currentUser: FirebaseUser? = firebaseAuth.currentUser

    private var callBack: CreateProfileFragment.SendProfileSettingsCallback? = null

    private val peopleDataBase = FirebaseDatabase.getInstance().getReference("people")


    fun getCurrentUser(): FirebaseUser?{
        currentUser = firebaseAuth.currentUser
        return currentUser
    }

    fun sendNewProfileSettings(studentModel: StudentModel, newCallBack: CreateProfileFragment.SendProfileSettingsCallback){
        callBack = newCallBack

        val checkUser = currentUser
        if (checkUser != null){
            peopleDataBase.child(checkUser.uid).setValue(studentModel)
            callBack?.onSuccess("Успешно сохранено")
        } else {
            callBack?.onError("Текущий пользователь неизвестен")
        }
    }
}