package com.benidict.data.interactor

import com.benidict.data.repository.UserRepository
import javax.inject.Inject

class CheckIsUserLoggedInUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend fun invoke() = repository.isUserLoggedIn()
}