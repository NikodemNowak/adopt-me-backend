package com.nikodemnowak.adoptme.dto

import java.util.*

data class PatchAnimalQuestionsDTO(
        val id: String,
        val animalType: String,
        val question: String,
        val number: Int
)