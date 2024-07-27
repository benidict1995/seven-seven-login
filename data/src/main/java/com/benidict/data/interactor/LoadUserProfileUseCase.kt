package com.benidict.data.interactor

import com.benidict.data.repository.UserRepository
import javax.inject.Inject

class LoadUserProfileUseCase @Inject constructor(
    private val repository: UserRepository
){
    suspend fun invoke() = repository.loadUserProfile()
}