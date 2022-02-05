package com.nikodemnowak.adoptme

data class ApiException(
    override val message: String,
    val errorCode: Int
) : RuntimeException() {
    companion object {
        const val EMAIL_TAKEN_ERROR_CODE = 1234
        const val PHONE_TAKEN_ERROR_CODE = 9182
    }
}

data class ErrorResponse(
    val message: String,
    val errorCode: Int
)