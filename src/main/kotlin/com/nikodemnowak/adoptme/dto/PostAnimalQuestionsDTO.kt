package com.nikodemnowak.adoptme.dto

import java.util.*
import javax.validation.constraints.NotEmpty

data class PostAnimalQuestionsDTO(
        @field:NotEmpty
        val animalType: String,
        val question: UUID,
        val number: Int
)