package com.benidict.persistence.module

import android.content.Context
import com.benidict.persistence.local.LocalSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun providesLocalSource(
       @ApplicationContext context: Context
    ) = LocalSource(
        context
    )

}