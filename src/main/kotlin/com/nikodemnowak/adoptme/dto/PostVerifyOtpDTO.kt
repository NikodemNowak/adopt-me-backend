package com.nikodemnowak.adoptme.dto

import java.util.*

data class PostVerifyOtpDTO(
        val otpCode: String,
        val session: UUID
)