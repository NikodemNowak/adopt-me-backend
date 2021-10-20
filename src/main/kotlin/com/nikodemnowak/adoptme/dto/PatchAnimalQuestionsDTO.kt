package com.nikodemnowak.adoptme.dto

import java.util.*

data class PatchAnimalQuestionsDTO(
        val id: UUID,
        val animalType: String,
        val question: UUID,
        val number: Int
)