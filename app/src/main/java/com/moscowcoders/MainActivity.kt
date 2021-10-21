package com.moscowcoders

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.moscowcoders.ui.authentication.AuthenticationHelper
import com.moscowcoders.ui.authentication.LoginFragment
import com.moscowcoders.ui.checkin.FragmentCheckIn
import com.moscowcoders.ui.list.FragmentListSportObjects

// Класс единственного Activity в приложении

class MainActivity: AppCompatActivity() {

    // Тег для логов
    private val TAG_ACTIVITY = "MyMainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.id_frame_container, FragmentListSportObjects())
            .commit()
    }

    fun showCheckInOrLoginFragment(id: String){

        val authenticationHelper = AuthenticationHelper()
        val currentUser = authenticationHelper.getCurrentUser()

        if(currentUser == null){
            showLoginFragment()
        } else{
            showCheckInFragment(id)
        }

    }

    private fun showCheckInFragment(id: String){
        val newArgs = Bundle()
        newArgs.putString("id", id)

        Log.d(TAG_ACTIVITY, "В активити получено: $id")

        val newFragment = FragmentCheckIn()
        newFragment.arguments = newArgs

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.id_frame_container, newFragment)
            .addToBackStack(null)
            .commit()
    }

    private fun showLoginFragment(){
        val newFragment = LoginFragment()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.id_frame_container, newFragment)
            .addToBackStack(null)
            .commit()
    }
}