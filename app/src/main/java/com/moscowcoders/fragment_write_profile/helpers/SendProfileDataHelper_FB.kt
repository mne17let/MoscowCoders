package com.moscowcoders.fragment_write_profile.helpers

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.moscowcoders.data.models.people.StudentModel
import com.moscowcoders.fragment_write_profile.ui.WriteProfileFragment

class SendProfileDataHelper_FB {

    private val TAG_CREATE_PROFILE_LISTENER = "MyFirebaseSendProfileSettingsHelper"

    private val firebaseAuth = FirebaseAuth.getInstance()
    private var currentUser: FirebaseUser? = firebaseAuth.currentUser

    private var callBack: WriteProfileFragment.SendProfileSettingsCallback? = null

    private val peopleDataBase = FirebaseDatabase.getInstance().getReference("people")


    fun getCurrentUser(): FirebaseUser?{
        currentUser = firebaseAuth.currentUser
        return currentUser
    }

    fun sendNewProfileSettings(studentModel: StudentModel, newCallBack: WriteProfileFragment.SendProfileSettingsCallback){
        callBack = newCallBack

        val checkUser = currentUser
        if (checkUser != null){
            peopleDataBase.child(checkUser.uid).setValue(studentModel)
            Log.d(TAG_CREATE_PROFILE_LISTENER, "Отправлен объект: $studentModel")

            callBack?.onSuccess("Ваши данные сохранены")
        } else {
            callBack?.onError("Текущий пользователь неизвестен")
        }
    }
}