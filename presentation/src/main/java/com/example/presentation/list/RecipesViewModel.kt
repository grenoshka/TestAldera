package com.example.presentation.list

import androidx.lifecycle.viewModelScope
import com.example.domain.entity.RecipeItem
import com.example.domain.usecase.GetRecipesListUseCase
import com.example.domain.util.State
import com.example.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val getRecipesListUseCase: GetRecipesListUseCase
) : BaseViewModel() {
    private var _recipes = MutableSharedFlow<List<RecipeItem>>()
    val recipes get() = _recipes.asSharedFlow()

    fun getRecipes() {
        viewModelScope.launch {
            _isLoading.emit(true)
            when (val result = getRecipesListUseCase()) {
                is State.Success -> {
                    _recipes.emit(result.data)
                }
                is State.Error -> {
                    _error.emit(result.error)
                }
            }
            _isLoading.emit(false)
        }
    }
}