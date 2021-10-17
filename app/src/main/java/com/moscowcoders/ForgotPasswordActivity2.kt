package com.moscowcoders

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ForgotPasswordActivity2: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgot_password_2)

    }

    fun forgotPasswordBack_2(view: View) {
        startActivity(Intent(this, ForgotPasswordActivity::class.java))
    }

    fun accountRecAcceptFinal(view: View) {
        startActivity(Intent(this, RecordsActivity::class.java))
    }
}
