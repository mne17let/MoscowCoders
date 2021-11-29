package com.moscowcoders.fragment_sport_objects_list.helpers

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.moscowcoders.data.models.people.StudentModel
import com.moscowcoders.fragment_sport_objects_list.ui.FragmentListSportObjects

class GetProfileListener_FB(newCallBack: FragmentListSportObjects.ProfileListenerCallBack): ValueEventListener {

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