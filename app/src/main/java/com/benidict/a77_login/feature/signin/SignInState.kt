package com.benidict.a77_login.feature.signin

import java.lang.Exception

sealed class SignInState {
    object NavigateToHome: SignInState()
    data class ShowError(val exception: String): SignInState()
}