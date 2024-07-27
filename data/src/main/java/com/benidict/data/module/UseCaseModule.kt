package com.benidict.data.module

import com.benidict.data.interactor.CheckIsUserLoggedInUseCase
import com.benidict.data.interactor.LoadUserProfileUseCase
import com.benidict.data.interactor.LoginUseCase
import com.benidict.data.interactor.SetUserLoggedInUseCase
import com.benidict.data.repository.UserRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Singleton
    @ViewModelScoped
    fun providesSetUserLoggedInUseCase(
        repository: UserRepository
    ) = SetUserLoggedInUseCase(repository)

    @Singleton
    @ViewModelScoped
    fun  providesCheckIsUserLoggedInUseCase(
        repository: UserRepository
    ) =
        CheckIsUserLoggedInUseCase(repository)

    @Singleton
    @ViewModelScoped
    fun provideLoginUseCase(
        repository: UserRepository
    ) = LoginUseCase(
        repository
    )

    @Singleton
    @ViewModelScoped
    fun provideLoadUserProfileUseCase(
        repository: UserRepository
    ) = LoadUserProfileUseCase(
        repository
    )
}