package com.example.testaldera.di

import com.example.data.network.ApiClient
import com.example.data.repository.RecipeRepositoryImpl
import com.example.domain.repository.RecipeRepository
import com.example.domain.util.ErrorHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {
    @Provides
    @ViewModelScoped
    fun recipeRepository(
        apiClient: ApiClient,
        errorHandler: ErrorHandler
    ): RecipeRepository = RecipeRepositoryImpl(apiClient, errorHandler)
}