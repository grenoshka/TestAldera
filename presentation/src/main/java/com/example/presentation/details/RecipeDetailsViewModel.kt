package com.example.presentation.details

import androidx.lifecycle.viewModelScope
import com.example.domain.entity.RecipeDetails
import com.example.domain.usecase.GetRecipeDetailsUseCase
import com.example.domain.util.State
import com.example.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    private val getRecipeDetailsUseCase: GetRecipeDetailsUseCase
) : BaseViewModel() {
    private var _recipeDetails = MutableSharedFlow<RecipeDetails>()
    val recipeDetails get() = _recipeDetails.asSharedFlow()

    fun getRecipeDetails(uuid: String) {
        viewModelScope.launch {
            _isLoading.emit(true)
            when (val result = getRecipeDetailsUseCase(uuid)) {
                is State.Success -> {
                    _recipeDetails.emit(result.data)
                }
                is State.Error -> {
                    _error.emit(result.error)
                }
            }
            _isLoading.emit(false)
        }
    }
}