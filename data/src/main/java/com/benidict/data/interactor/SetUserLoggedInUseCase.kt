package com.benidict.data.interactor

import com.benidict.data.repository.UserRepository
import javax.inject.Inject

class SetUserLoggedInUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend fun invoke(isLoggedIn: Boolean) = repository.userLoggedIn(isLoggedIn)
}