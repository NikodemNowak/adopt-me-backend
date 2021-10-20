package com.nikodemnowak.adoptme.dto

import java.util.*

data class AnimalDTO(
    val animalId: UUID,
    val animalType: String,
    val race: String,
    val color: String,
    val shelter: UUID?,
    val privateOwner: UUID?,
    val age: Int,
    val name: String?
)