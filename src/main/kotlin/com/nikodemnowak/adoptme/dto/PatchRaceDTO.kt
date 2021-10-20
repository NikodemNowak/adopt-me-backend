package com.nikodemnowak.adoptme.dto

import java.util.*

data class PatchRaceDTO(
        val id: UUID,
        val raceName: String,
        val animalType: String
)