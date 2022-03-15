package com.example.domain.usecase

import com.example.domain.entity.RecipeDetails
import com.example.domain.repository.RecipeRepository
import com.example.domain.util.State
import javax.inject.Inject

class GetRecipeDetailsUseCase @Inject constructor(private val recipeRepository: RecipeRepository) {
    suspend operator fun invoke(uuid: String): State<RecipeDetails> {
        return recipeRepository.getRecipeDetails(uuid)
    }
}