package com.benidict.a77_login.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benidict.data.interactor.LoadUserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val loadUserProfileUseCase: LoadUserProfileUseCase
) : ViewModel() {

    private val _state: MutableSharedFlow<HomeState> = MutableSharedFlow()
    val state = _state.asSharedFlow()

    fun loadUserProfile() {
        viewModelScope.launch {
            val invoke = loadUserProfileUseCase.invoke()
            _state.emit(HomeState.LoadUserProfile(invoke))
        }
    }
}