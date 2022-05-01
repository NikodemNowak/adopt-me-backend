package com.nikodemnowak.adoptme.dto

data class PostVerifyPinDTO(
    val pin: String,
    val session: String
)