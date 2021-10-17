package com.moscowcoders

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

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
