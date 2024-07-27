package com.benidict.network.module

import android.content.Context
import com.benidict.network.remote.RemoteSource
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Singleton
    @Provides
    fun providesRemoteSource(
        @ApplicationContext appContext: Context,
        gson: Gson
    ) = RemoteSource(
        appContext,
        gson
    )

    @Singleton
    @Provides
    fun providesGson() = Gson()
}