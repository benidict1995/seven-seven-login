package com.benidict.data.repository

import com.benidict.domain.model.User
import com.benidict.network.remote.RemoteSource
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val remoteSource: RemoteSource
) {
    suspend fun loadUserProfile(): User {
        return remoteSource.loadUserProfile()
    }

    suspend fun login(username: String, password: String): User {
        return remoteSource.login(
            username, password
        )
    }
}