package com.example.domain.repository

import com.example.domain.entity.RecipeDetails
import com.example.domain.entity.RecipeItem
import com.example.domain.util.State

interface RecipeRepository {
    suspend fun getRecipesList(): State<List<RecipeItem>>
    suspend fun getRecipeDetails(uuid: String): State<RecipeDetails>
}