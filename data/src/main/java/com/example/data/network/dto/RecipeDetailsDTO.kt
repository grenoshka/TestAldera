package com.example.data.network.dto

data class RecipeDetailsDTO(
    val uuid: String,
    val name: String,
    val images: List<String>,
    val description: String?,
    val instructions: String,
    val difficulty: Int,
    val similar: List<SimilarRecipeDTO>
)