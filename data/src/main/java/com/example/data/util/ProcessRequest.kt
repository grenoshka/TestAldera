package com.example.data.util

import com.example.domain.util.ErrorHandler
import com.example.domain.util.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T> processRequest(
    errorHandler: ErrorHandler,
    request: suspend () -> T
) = withContext(Dispatchers.IO) {
    try {
        val result = request()
        State.Success(result)
    } catch (e: Exception) {
        val error = errorHandler(e)
        State.Error(error)
    }
}