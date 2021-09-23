package com.proway.testproject.repository.interfaces

interface IGithubUsersResult {
    fun successAPI()
    fun failureAPI()
    fun successFirebase()
    fun failureFirebase()
}