package com.benidict.data.repository

import com.benidict.domain.model.User
import com.benidict.network.remote.RemoteSource
import com.benidict.persistence.local.LocalSource
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val localSource: LocalSource,
    private val remoteSource: RemoteSource
) {

    fun userLoggedIn(isLoggedIn: Boolean) {
        localSource.loggedIn = isLoggedIn
    }

    fun isUserLoggedIn(): Boolean {
        return localSource.loggedIn
    }

    suspend fun loadUserProfile(): User {
        return remoteSource.loadUserProfile()
    }

    suspend fun login(username: String, password: String): User {
        return remoteSource.login(
            username, password
        )
    }
}