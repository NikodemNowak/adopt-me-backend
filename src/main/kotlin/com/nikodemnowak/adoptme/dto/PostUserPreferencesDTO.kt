package com.nikodemnowak.adoptme.dto

import java.util.*
import javax.validation.constraints.NotEmpty

data class PostUserPreferencesDTO(
        @field:NotEmpty
        val user: UUID,
        val questionAnswers: UUID,
        val answer: String
)