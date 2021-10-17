package com.moscowcoders

import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.graphics.toColor
import androidx.transition.TransitionManager
import com.moscowcoders.ui.FragmentListSportObjects

//import android.R





class MainActivity: AppCompatActivity() {

    lateinit var reg_button: TextView
    lateinit var log_in_button: TextView
    lateinit var main_log_in_button: Button

//    lateinit var constraintLayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        reg_button = findViewById<View>(R.id.reg_button) as TextView
        log_in_button = findViewById<View>(R.id.log_in_button) as TextView
        main_log_in_button = findViewById<View>(R.id.main_log_in_button) as Button

    }


    fun forgotPassword(view: View) {
        startActivity(Intent(this, ForgotPasswordActivity::class.java))
    }

    fun loginClick(view: View) {
        startActivity(Intent(this, RecordsActivity::class.java))
    }

    fun regClick(view: View) {
        reg_button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25f)
        log_in_button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)

        reg_button.setTextColor(getResources().getColor(R.color.main_blue, null))
        log_in_button.setTextColor(getResources().getColor(R.color.main_blue60, null))

        main_log_in_button.setText(getResources().getText(R.string.create))

//        val set = ConstraintSet()
//        set.clone(constraintLayout)
//        changeConstraints(set)
//        TransitionManager.beginDelayedTransition(constraintLayout)
//        set.applyTo(constraintLayout)

    }

//    private fun changeConstraints(set: ConstraintSet) {
//        set.clear(R.id.log_in_button, ConstraintSet.LEFT)
//    }

    fun logInClick(view: View) {
        reg_button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
        log_in_button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25f)

        reg_button.setTextColor(getResources().getColor(R.color.main_blue60, null))
        log_in_button.setTextColor(getResources().getColor(R.color.main_blue, null))

        main_log_in_button.setText(getResources().getText(R.string.login))
    }

}