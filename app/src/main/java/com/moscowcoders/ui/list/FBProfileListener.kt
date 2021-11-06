package com.moscowcoders.ui.list

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.moscowcoders.data.models.people.StudentModel

class FBProfileListener(newCallBack: FragmentListSportObjects.ProfileListenerCallBack): ValueEventListener {

    private val TAG_USER_PROFILE_LISTENER = "MyFBProfileListener"

    var callBack: FragmentListSportObjects.ProfileListenerCallBack?

    init {
        callBack = newCallBack
    }

    override fun onDataChange(snapshot: DataSnapshot) {
        val newStudentProfile = snapshot.getValue(StudentModel::class.java)
        if (newStudentProfile != null){
            callBack?.onSuccess(newStudentProfile)

            Log.d(TAG_USER_PROFILE_LISTENER, "Пришёл объект: $newStudentProfile")
        } else{
            callBack?.onError()
        }
        callBack = null
    }

    override fun onCancelled(error: DatabaseError) {
        callBack?.onError()
        callBack = null
    }
}