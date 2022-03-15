package com.example.domain.util

import com.example.domain.entity.ErrorEntity

sealed class State<T>(
    open val data: T? = null,
    open val error: ErrorEntity? = null
) {
    class Success<T>(override val data: T) : State<T>(data)
    class Error<T>(override val error: ErrorEntity, data: T? = null) : State<T>(data, error)
}