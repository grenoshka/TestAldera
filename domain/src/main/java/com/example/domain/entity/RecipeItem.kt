package com.example.domain.entity

data class RecipeItem(
    val uuid: String,
    val name: String,
    val images: List<String>,
    val description: String
)