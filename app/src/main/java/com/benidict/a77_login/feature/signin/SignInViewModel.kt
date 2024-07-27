package com.benidict.a77_login.feature.signin

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benidict.data.interactor.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _state: MutableSharedFlow<SignInState> = MutableSharedFlow()
    val state = _state.asSharedFlow()

    val userNameState: MutableStateFlow<String> = MutableStateFlow("")
    val passwordState: MutableStateFlow<String> = MutableStateFlow("")

    fun login() {
        viewModelScope.launch {
            val invoke = loginUseCase.invoke(
                username = userNameState.value,
                password = passwordState.value
            )
            _state.emit(
                if (invoke.email.isNotEmpty()) {
                    SignInState.NavigateToHome
                } else {
                    SignInState.ShowError(
                        "incorrect username or password!"
                    )
                }
            )
        }
    }
}