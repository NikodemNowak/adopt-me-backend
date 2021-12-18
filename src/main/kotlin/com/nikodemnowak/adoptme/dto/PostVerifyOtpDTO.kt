package com.nikodemnowak.adoptme.dto


data class PostVerifyOtpDTO(
        val otpCode: String,
        val session: String
)