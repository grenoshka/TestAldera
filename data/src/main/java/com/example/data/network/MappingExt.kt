package com.example.data.network

import com.example.data.network.dto.*
import com.example.domain.entity.Difficulty
import com.example.domain.entity.RecipeDetails
import com.example.domain.entity.RecipeItem
import com.example.domain.entity.SimilarRecipe

fun RecipesDTO.toEntityList(): List<RecipeItem> =
    list.map { it.toEntity() }

fun RecipeItemDTO.toEntity(): RecipeItem =
    RecipeItem(
        uuid = uuid,
        name = name,
        images = images,
        description = description ?: ""
    )

fun RecipeDTO.toEntity(): RecipeDetails =
    details.toEntity()

fun RecipeDetailsDTO.toEntity(): RecipeDetails =
    RecipeDetails(
        uuid = uuid,
        name = name,
        images = images,
        description = description ?: "",
        instructions = instructions,
        difficulty = difficulty.toDifficultyEnum(),
        similar = similar.toEntityList()
    )

fun Int.toDifficultyEnum(): Difficulty =
    when (this) {
        1 -> Difficulty.VeryEasy
        2 -> Difficulty.Easy
        3 -> Difficulty.Middle
        4 -> Difficulty.Hard
        5 -> Difficulty.VeryHard
        else -> throw NoWhenBranchMatchedException()
    }

fun List<SimilarRecipeDTO>.toEntityList(): List<SimilarRecipe> =
    this.map { it.toEntity() }

fun SimilarRecipeDTO.toEntity(): SimilarRecipe =
    SimilarRecipe(
        uuid = uuid,
        name = name,
        image = image
    )