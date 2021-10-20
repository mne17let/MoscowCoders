package com.moscowcoders.EgorCode

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.moscowcoders.MainActivity
import com.moscowcoders.R

class ForgotPasswordActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgot_password_1)

    }

    fun forgotPasswordBack_1(view: View) {
        startActivity(Intent(this, MainActivity::class.java))
    }

    fun accountRecAccept(view: View) {
        startActivity(Intent(this, ForgotPasswordActivity2::class.java))
    }
}
