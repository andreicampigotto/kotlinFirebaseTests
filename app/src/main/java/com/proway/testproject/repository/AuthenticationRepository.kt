package com.proway.testproject.repository

import com.google.firebase.auth.FirebaseAuth
import com.proway.testproject.repository.interfaces.ISignInResult

class AuthenticationRepository(
    private val observer: ISignInResult,
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
) {
    fun signIn(eMail: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(eMail, password).apply {
            if (this.isSuccessful) observer.success(eMail, password)
            else observer.failure(eMail, password)
        }
    }
}