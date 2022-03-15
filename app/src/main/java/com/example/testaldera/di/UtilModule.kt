package com.example.testaldera.di

import com.example.data.util.ErrorHandlerImpl
import com.example.domain.util.ErrorHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UtilModule {
    @Provides
    @Singleton
    fun errorHandler(): ErrorHandler = ErrorHandlerImpl()
}