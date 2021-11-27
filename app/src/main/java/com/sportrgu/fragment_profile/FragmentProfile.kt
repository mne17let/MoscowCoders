package com.sportrgu.fragment_profile

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.sportrgu.MainActivity
import com.sportrgu.R
import com.sportrgu.data.models.people.StudentModel

class FragmentProfile: Fragment(R.layout.fragment_profile) {

    private val TAG_FRAGMENT_PROFILE = "MyProfileFragment"

    private val fDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val userReference = fDatabase.getReference("people")

    private val currentUser = FirebaseAuth.getInstance().currentUser
    private var userInfo: StudentModel? = null

    private lateinit var button_edit_profile: Button
    private lateinit var button_show_bookings: Button
    private lateinit var button_sign_out: Button

    private lateinit var name_textview: TextView
    private lateinit var group_textview: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        button_edit_profile = view.findViewById(R.id.id_edit_profile_button)
        button_show_bookings = view.findViewById(R.id.id_open_bookings_button)
        button_sign_out = view.findViewById(R.id.id_sign_out_button)

        name_textview = view.findViewById(R.id.id_firstname_lastname_profile_textview)
        group_textview = view.findViewById(R.id.id_group_profile_textview)

        setUpUserInfo()
        setUpEditProfileButton()
        setUpShowBookingsButton()
        setUpSignOutButton()
    }

    private fun setUpUserInfo(){
        if (currentUser != null){
            val userRef = userReference.child(currentUser.uid)

            userRef.addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    userInfo = snapshot.getValue(StudentModel::class.java)
                    setUpInfoBox()
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d(TAG_FRAGMENT_PROFILE, "На экране редактирования профиля не получены данные о текущем пользователе")

                    name_textview.text = "Вы не вошли в аккаунт"
                }

            })
        }
    }

    private fun setUpInfoBox(){
        val fullName = "${userInfo?.firstName} ${userInfo?.lastName}"
        name_textview.text = fullName
        group_textview.text = userInfo?.studyGroup
    }

    private fun setUpShowBookingsButton(){
        button_show_bookings.setOnClickListener {
            (activity as MainActivity).showBookingsFragment()
        }
    }

    private fun setUpSignOutButton(){
        button_sign_out.setOnClickListener {
            // FirebaseAuth.getInstance().signOut()
            (activity as MainActivity).showSportObjectsListAfterSignOut()
        }
    }

    private fun setUpEditProfileButton(){
        button_edit_profile.setOnClickListener {
            Toast.makeText(requireContext(), "Когда-нибудь вы сможете редактировать свой профиль", Toast.LENGTH_SHORT).show()
        }
    }
}