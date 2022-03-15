package com.example.data.network

import com.example.data.network.dto.RecipeDTO
import com.example.data.network.dto.RecipesDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiClient {
    @GET("recipes")
    suspend fun getRecipesList(): RecipesDTO

    @GET("recipes/{uuid}")
    suspend fun getRecipeDetails(@Path("uuid") uuid: String): RecipeDTO
}