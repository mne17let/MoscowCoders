package com.moscowcoders.ui.authentication

import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.lang.Exception
import javax.security.auth.callback.Callback

class AuthenticationHelper {

    private var callback: LoginFragment.AuthenticationCallback? = null

    private val firebaseAuth = FirebaseAuth.getInstance()
    private var currentUser: FirebaseUser? = firebaseAuth.currentUser

    fun getCurrentUser(): FirebaseUser?{
        currentUser = firebaseAuth.currentUser
        return currentUser
    }

    fun createNewUser(email: String, pass: String, newCallback: LoginFragment.AuthenticationCallback){
        callback = newCallback

        val task: Task<AuthResult> = firebaseAuth.createUserWithEmailAndPassword(email, pass)
        task.addOnCompleteListener(completeListener)
    }

    private val completeListener = object : OnCompleteListener<AuthResult>{

        override fun onComplete(task: Task<AuthResult>) {
            if(task.isSuccessful){
                currentUser = firebaseAuth.currentUser
                currentUser?.let { callback?.onSuccess(it) }

                callback = null
            } else{
                val error: Exception? = task.exception
                callback?.onError(error.toString())

                callback = null
            }
        }

    }

    fun signIn(email: String, pass: String, newCallback: LoginFragment.AuthenticationCallback){
        callback = newCallback

        val task = firebaseAuth.signInWithEmailAndPassword(email, pass)
        task.addOnCompleteListener{ completeTask ->
            if (completeTask.isSuccessful){
                currentUser = firebaseAuth.currentUser
                currentUser?.let { callback?.onSuccess(it) }

                callback = null
            } else{
                val error: Exception? = task.exception
                callback?.onError(error.toString())

                callback = null
            }
        }
    }

    fun signOut(){
        firebaseAuth.signOut()
    }
}