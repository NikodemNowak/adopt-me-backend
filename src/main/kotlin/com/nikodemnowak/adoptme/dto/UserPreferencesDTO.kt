package com.nikodemnowak.adoptme.dto

data class UserPreferencesDTO(
        val id: String,
        val user: String,
        val questionAnswers: String,
        val answer: String
)