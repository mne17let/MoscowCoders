/*package com.moscowcoders.EgorCode

import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.TransitionManager

import com.moscowcoders.R
import com.moscowcoders.ui.list.FragmentListSportObjects

//import android.R



class EgorMainActivity: AppCompatActivity() {

    lateinit var reg_button: TextView
    lateinit var log_in_button: TextView
    lateinit var main_log_in_button: Button
    lateinit var add_user: ImageButton
    lateinit var constraint_layout: ConstraintLayout
    lateinit var forgot_password: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.id_frame_container, FragmentListSportObjects())
            .commit()

        setContentView(R.layout.fragment_login)



        *//*reg_button = findViewById<View>(R.id.reg_button) as TextView
        log_in_button = findViewById<View>(R.id.log_in_button) as TextView
        forgot_password = findViewById<View>(R.id.forgot_password) as TextView
        main_log_in_button = findViewById<View>(R.id.main_log_in_button) as Button
        add_user = findViewById<View>(R.id.add_user) as ImageButton
        constraint_layout = findViewById<View>(R.id.log_reg_button_box) as ConstraintLayout*//*
    }


    fun forgotPassword(view: View) {
        startActivity(Intent(this, ForgotPasswordActivity::class.java))
    }

    fun loginClick(view: View) {
        startActivity(Intent(this, EgorMainActivity::class.java))
    }


    fun regClick(view: View) {
        reg_button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25f)
        log_in_button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
        reg_button.setTextColor(getResources().getColor(R.color.main_blue, null))
        log_in_button.setTextColor(getResources().getColor(R.color.main_blue60, null))
        forgot_password.visibility = View.INVISIBLE
        main_log_in_button.setText(R.string.create)
        add_user.setImageResource(R.drawable.ic_add_avatar)

        val set = ConstraintSet()
        set.clone(constraint_layout)
        changeConstraints_reg(set)
        TransitionManager.beginDelayedTransition(constraint_layout)
        set.applyTo(constraint_layout)
    }

    *//*private fun changeConstraints_reg(set: ConstraintSet) {
        set.clear(R.id.log_in_button, ConstraintSet.END)
        set.connect(R.id.reg_button, ConstraintSet.START, R.id.log_reg_button_box, ConstraintSet.START);
    }*//*


    fun logInClick(view: View) {
        reg_button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
        log_in_button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25f)
        reg_button.setTextColor(getResources().getColor(R.color.main_blue60, null))
        log_in_button.setTextColor(getResources().getColor(R.color.main_blue, null))
        forgot_password.visibility = View.VISIBLE
        main_log_in_button.setText(R.string.login)
        add_user.setImageResource(R.color.transparent100)

        val set = ConstraintSet()
        set.clone(constraint_layout)
        changeConstraints_log_in(set)
        TransitionManager.beginDelayedTransition(constraint_layout)
        set.applyTo(constraint_layout)
    }

    private fun changeConstraints_log_in(set: ConstraintSet) {
        set.clear(R.id.reg_button, ConstraintSet.START)
        set.connect(R.id.log_in_button, ConstraintSet.END, R.id.log_reg_button_box, ConstraintSet.END);
    }

}*/
