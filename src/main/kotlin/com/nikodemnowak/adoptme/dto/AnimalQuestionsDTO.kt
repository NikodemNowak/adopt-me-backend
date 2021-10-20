package com.nikodemnowak.adoptme.dto

import java.util.*

data class AnimalQuestionsDTO(
        val id: UUID,
        val animalType: String,
        val question: String,
        val number: Int
)