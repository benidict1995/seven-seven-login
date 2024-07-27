package com.benidict.a77_login.feature.home

import com.benidict.domain.model.User

sealed class HomeState {
    data class LoadUserProfile(
        val user: User
    ): HomeState()
}