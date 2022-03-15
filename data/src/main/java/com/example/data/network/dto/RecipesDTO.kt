package com.example.data.network.dto

import com.google.gson.annotations.SerializedName

data class RecipesDTO(
    @SerializedName("recipes")
    val list: List<RecipeItemDTO>
)