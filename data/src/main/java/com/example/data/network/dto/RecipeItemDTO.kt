package com.example.data.network.dto

data class RecipeItemDTO(
    val uuid: String,
    val name: String,
    val images: List<String>,
    val description: String?
)