package com.moscowcoders

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.moscowcoders.ui.authentication.FirebaseAuthenticationHelper
import com.moscowcoders.ui.authentication.LoginFragment
import com.moscowcoders.ui.checkin.FragmentCheckIn
import com.moscowcoders.ui.list.FragmentListSportObjects
import com.moscowcoders.ui.profile.firstCreateProfile.CreateProfileFragment

// Класс единственного Activity в приложении

class MainActivity: AppCompatActivity() {

    // Тег для логов
    private val TAG_ACTIVITY = "MyMainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showStartFragment()
    }

    fun showCheckInOrLoginFragment(id: String){

        val authenticationHelper = FirebaseAuthenticationHelper()
        val currentUser = authenticationHelper.getCurrentUser()

        if(currentUser == null){
            showLoginFragment()
        } else{
            showCheckInFragment(id)
        }

    }

    fun showSportObjectsListAfterLogIn(){
        showSportObjectsList()
    }

    private fun showStartFragment(){
        showSportObjectsList()
    }

    private fun showSportObjectsList(){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.id_frame_container, FragmentListSportObjects())
            .commit()
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

    fun showFirstProfileSettings(){
        val newFragment = CreateProfileFragment()

        supportFragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.id_frame_container, newFragment)
            .commit()
    }
}