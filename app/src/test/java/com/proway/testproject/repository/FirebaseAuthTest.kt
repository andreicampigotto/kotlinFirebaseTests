package com.proway.testproject.repository

import android.app.Activity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.common.truth.Truth.assertThat
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.proway.testproject.repository.interfaces.ISignInResult
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.concurrent.Executor

@RunWith(JUnit4::class)
class FirebaseAuthTest : ISignInResult {
    private lateinit var successTask: Task<AuthResult>
    private lateinit var failureTask: Task<AuthResult>

    @Mock
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var authenticationRepository: AuthenticationRepository

    private var logInResult = UNDEFINED

    companion object {
        private const val SUCCESS = 1
        private const val FAILURE = -1
        private const val UNDEFINED = 0
    }

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        successTask = object : Task<AuthResult>() {

            override fun addOnCompleteListener(
                executor: Executor,
                onCompleteListener: OnCompleteListener<AuthResult>
            ): Task<AuthResult> {

                onCompleteListener.onComplete(successTask)
                return successTask
            }

            override fun isComplete(): Boolean = true

            override fun isSuccessful(): Boolean = true

            override fun isCanceled(): Boolean = true

            override fun getResult(): AuthResult? {
                TODO("Not yet implemented")
            }

            override fun <X : Throwable?> getResult(p0: Class<X>): AuthResult? {
                TODO("Not yet implemented")
            }

            override fun getException(): Exception? {
                TODO("Not yet implemented")
            }

            override fun addOnSuccessListener(p0: OnSuccessListener<in AuthResult>): Task<AuthResult> {
                return this
            }

            override fun addOnSuccessListener(
                p0: Executor,
                p1: OnSuccessListener<in AuthResult>
            ): Task<AuthResult> {
                TODO("Not yet implemented")
            }

            override fun addOnSuccessListener(
                p0: Activity,
                p1: OnSuccessListener<in AuthResult>
            ): Task<AuthResult> {
                TODO("Not yet implemented")
            }

            override fun addOnFailureListener(p0: OnFailureListener): Task<AuthResult> {
                return this
            }

            override fun addOnFailureListener(
                p0: Executor,
                p1: OnFailureListener
            ): Task<AuthResult> {
                TODO("Not yet implemented")
            }

            override fun addOnFailureListener(
                p0: Activity,
                p1: OnFailureListener
            ): Task<AuthResult> {
                TODO("Not yet implemented")
            }
        }

        failureTask= object : Task<AuthResult>(){

            override fun addOnCompleteListener(
                executor: Executor,
                onCompleteListener: OnCompleteListener<AuthResult>
            ): Task<AuthResult> {
                onCompleteListener.onComplete(failureTask)
                return failureTask
            }

            override fun isComplete(): Boolean = true

            override fun isSuccessful(): Boolean = false

            override fun isCanceled(): Boolean {
                return false
            }

            override fun getResult(): AuthResult? {
                return result
            }

            override fun <X : Throwable?> getResult(p0: Class<X>): AuthResult? {
                return result
            }

            override fun getException(): java.lang.Exception? {
                return exception
            }

            override fun addOnSuccessListener(p0: OnSuccessListener<in AuthResult>): Task<AuthResult> {
                return this
            }

            override fun addOnSuccessListener(
                p0: Executor,
                p1: OnSuccessListener<in AuthResult>
            ): Task<AuthResult> {
                return this
            }

            override fun addOnSuccessListener(
                p0: Activity,
                p1: OnSuccessListener<in AuthResult>
            ): Task<AuthResult> {
                return this
            }

            override fun addOnFailureListener(p0: OnFailureListener): Task<AuthResult> {
                return this
            }

            override fun addOnFailureListener(
                p0: Executor,
                p1: OnFailureListener
            ): Task<AuthResult> {
                return this
            }

            override fun addOnFailureListener(
                p0: Activity,
                p1: OnFailureListener
            ): Task<AuthResult> {
                return this
            }

        }
        authenticationRepository = AuthenticationRepository(this, firebaseAuth)
    }

    @Test
    fun logInSuccess_test(){
        val email = "andrei@gmail.com"
        val password = "123456"
        Mockito.`when`(firebaseAuth.signInWithEmailAndPassword(email, password))
            .thenReturn(successTask)
        authenticationRepository.signIn(email, password)
        assertThat(logInResult).isEqualTo(SUCCESS)
    }

    @Test
    fun logInFailure_test(){
        val email = "andrei@gmail.com"
        val password = "123456"
        Mockito.`when`(firebaseAuth.signInWithEmailAndPassword(email, password))
            .thenReturn(failureTask)
        authenticationRepository.signIn(email, password)
        assertThat(logInResult).isEqualTo(FAILURE)
    }


    override fun success(email: String, password: String) {
        logInResult = SUCCESS
    }

    override fun failure(email: String, password: String) {
        logInResult = FAILURE
    }

}