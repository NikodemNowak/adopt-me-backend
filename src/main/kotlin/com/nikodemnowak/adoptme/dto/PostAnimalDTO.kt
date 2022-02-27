package com.nikodemnowak.adoptme.dto

import java.util.*
import javax.validation.constraints.NotEmpty

data class PostAnimalDTO(
    @field:NotEmpty
    val animalType: String,
    val race: String,
    val color: String,
    val age: Int,

    val privateOwner: UUID?,
    val shelter: UUID?,
    val name: String?,
    val photo: String?,
    val description: String?
)