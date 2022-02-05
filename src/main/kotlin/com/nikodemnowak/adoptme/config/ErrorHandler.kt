package com.nikodemnowak.adoptme.config

import com.nikodemnowak.adoptme.ApiException
import com.nikodemnowak.adoptme.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ErrorHandler {
    @ExceptionHandler(ApiException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleApiException(apiException: ApiException): ErrorResponse {
        return apiException.toErrorResponse()
    }
}

fun ApiException.toErrorResponse() = ErrorResponse(
    message = message,
    errorCode = errorCode
)