package com.nikodemnowak.adoptme.dto

import java.util.*

data class RaceDTO(
        val id: UUID,
        val raceName: String,
        val animalType: String
)