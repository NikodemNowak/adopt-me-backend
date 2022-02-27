package com.nikodemnowak.adoptme.dto

import java.util.*

data class PatchAnimalDTO(
        val id: UUID,
        val animalType: String,
        val race: String,
        val color: String,
        val privateOwner: UUID?,
        val shelter: UUID?,
        val age: Int,
        val name: String?,
        val photo: String?,
        val description: String?
)