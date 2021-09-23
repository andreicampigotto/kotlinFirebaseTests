package com.proway.testproject.service

import com.proway.testproject.model.GitUserModel
import retrofit2.Response
import retrofit2.http.GET

interface GithubAPI {
    @GET("/users")
    suspend fun fetchUsers(
    ): Response<List<GitUserModel>>
}