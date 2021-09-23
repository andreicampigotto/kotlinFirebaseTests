package com.proway.testproject.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.proway.testproject.model.GitUserModel
import com.proway.testproject.repository.interfaces.IGithubUsersResult
import com.proway.testproject.service.GithubAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GithubRepository(
    private val api: GithubAPI,
    private val observer: IGithubUsersResult,
    private val firebaseDb: FirebaseFirestore
) {
    companion object {
        const val COLLECTION_NAME = "github_users"
    }

    suspend fun fetchUsers() {
        val response = withContext(Dispatchers.Default) {
            api.fetchUsers()
        }
        if (response.isSuccessful) observer.successAPI() else observer.failureAPI()
    }

    fun saveUserOnFirebase(user: GitUserModel) {
        val task = firebaseDb.collection(COLLECTION_NAME).add(user)
        if (task.isSuccessful) observer.successFirebase() else observer.failureAPI()
    }
}