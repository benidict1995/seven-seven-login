package com.benidict.data.module

import com.benidict.data.repository.UserRepository
import com.benidict.network.remote.RemoteSource
import com.benidict.persistence.local.LocalSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideUserRepository(
        localSource: LocalSource,
        remoteSource: RemoteSource
    ) = UserRepository(
        localSource,
        remoteSource
    )
}