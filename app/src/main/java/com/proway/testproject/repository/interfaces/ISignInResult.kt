package com.proway.testproject.repository.interfaces

interface ISignInResult {
    fun success(email: String, password: String)
    fun failure(email: String, password: String)
}