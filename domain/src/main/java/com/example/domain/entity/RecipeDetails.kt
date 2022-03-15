package com.example.domain.entity

data class RecipeDetails(
    val uuid: String,
    val name: String,
    val images: List<String>,
    val description: String,
    val instructions: String,
    val difficulty: Difficulty,
    val similar: List<SimilarRecipe>
)