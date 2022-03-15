package com.example.data.network.dto

import com.google.gson.annotations.SerializedName

data class RecipeDTO(
    @SerializedName("recipe")
    val details: RecipeDetailsDTO
)