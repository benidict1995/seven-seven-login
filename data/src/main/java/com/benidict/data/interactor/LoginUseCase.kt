package com.benidict.data.interactor

import com.benidict.data.repository.UserRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: UserRepository
) {

    suspend fun invoke(username: String, password: String) =
        repository.login(username, password)
}