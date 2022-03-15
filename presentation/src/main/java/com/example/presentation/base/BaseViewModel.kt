package com.example.presentation.base

import androidx.lifecycle.ViewModel
import com.example.domain.entity.ErrorEntity
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel : ViewModel() {
    protected var _isLoading = MutableStateFlow(true)
    val isLoading get() = _isLoading.asStateFlow()

    protected var _error = MutableSharedFlow<ErrorEntity>()
    val error get() = _error.asSharedFlow()

}