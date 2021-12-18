package com.nikodemnowak.adoptme.dto

import javax.validation.constraints.NotEmpty

data class PostUserPreferencesDTO(
        @field:NotEmpty
        val user: String,
        val questionAnswers: String,
        val answer: String
)