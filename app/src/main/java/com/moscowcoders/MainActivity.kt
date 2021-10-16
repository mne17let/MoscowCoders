package com.moscowcoders

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.moscowcoders.ui.FragmentListSportObjects

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.id_frame_container, FragmentListSportObjects()).commit()
    }
}