package com.example.domain.usecase

import com.example.domain.entity.RecipeItem
import com.example.domain.repository.RecipeRepository
import com.example.domain.util.State
import javax.inject.Inject

class GetRecipesListUseCase @Inject constructor(private val recipeRepository: RecipeRepository) {
    suspend operator fun invoke(): State<List<RecipeItem>> {
        return recipeRepository.getRecipesList()
    }
}