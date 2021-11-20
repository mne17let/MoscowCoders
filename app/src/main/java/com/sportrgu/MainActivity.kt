package com.sportrgu

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GetTokenResult
import com.sportrgu.data.cloud.FirebaseApi
import com.sportrgu.data.models.people.Booking
import com.sportrgu.fragment_login.ui.LoginFragment
import com.sportrgu.fragment_check_in.ui.FragmentCheckIn
import com.sportrgu.fragment_sport_objects_list.helpers.TimeHelper
import com.sportrgu.fragment_sport_objects_list.ui.FragmentListSportObjects
import com.sportrgu.fragment_write_profile.ui.WriteProfileFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

// Класс единственного Activity в приложении

class MainActivity: AppCompatActivity() {

    // Тег для логов
    private val TAG_ACTIVITY = "MyMainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showStartFragment()
    }

    fun showCheckInOrLoginFragment(sportObjectId: String, haveProfile: Boolean, haveAccount: Boolean){

        if(!haveAccount){
            showLoginFragment()
        } else{
            showCheckInFragment(sportObjectId)
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
        val newFragment = WriteProfileFragment()

        supportFragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.id_frame_container, newFragment)
            .commit()
    }

    /*fun getPeople(){
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null){
            currentUser.getIdToken(true).addOnCompleteListener(object : OnCompleteListener<GetTokenResult>{
                override fun onComplete(task: Task<GetTokenResult>) {
                    if (task.isSuccessful){
                        Log.d(TAG_ACTIVITY, "Получен токен: ${task.result?.token}")
                        val retrofit = Retrofit
                            .Builder()
                            .baseUrl("https://moscowcoders-default-rtdb.firebaseio.com")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()

                        val api = retrofit.create(FirebaseApi::class.java)

                        val token = task.result?.token

                        if (token != null){

                        }

                    } else{
                        Log.d(TAG_ACTIVITY, "Ошибка получения токена: ${task.result?.token}")
                    }
                }

            })
        }
    }*/
}