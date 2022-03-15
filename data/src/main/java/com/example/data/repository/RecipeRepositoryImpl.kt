package com.example.data.repository

import com.example.data.network.ApiClient
import com.example.data.network.toEntity
import com.example.data.network.toEntityList
import com.example.data.util.processRequest
import com.example.domain.repository.RecipeRepository
import com.example.domain.util.ErrorHandler
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val apiClient: ApiClient,
    private val errorHandler: ErrorHandler
) : RecipeRepository {
    override suspend fun getRecipesList() = processRequest(errorHandler) {
        apiClient.getRecipesList().toEntityList()
    }

    override suspend fun getRecipeDetails(uuid: String) = processRequest(errorHandler) {
        apiClient.getRecipeDetails(uuid).toEntity()
    }
}