package com.nikodemnowak.adoptme.dto

import java.util.*

data class PostUserPinDTO(
        val pin: String,
        val session: String
)