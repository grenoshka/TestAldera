package com.example.domain.entity

sealed class ErrorEntity {
    object Network : ErrorEntity()
    object IncorrectInfo : ErrorEntity()
    object NotFound : ErrorEntity()
    object AccessDenied : ErrorEntity()
    object ServiceUnavailable : ErrorEntity()
    object Unknown : ErrorEntity()
}