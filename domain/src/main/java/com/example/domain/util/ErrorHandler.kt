package com.example.domain.util

import com.example.domain.entity.ErrorEntity

interface ErrorHandler {
    operator fun invoke(throwable: Throwable): ErrorEntity
}