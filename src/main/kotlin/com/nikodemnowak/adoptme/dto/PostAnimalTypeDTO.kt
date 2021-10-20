package com.nikodemnowak.adoptme.dto

import javax.validation.constraints.NotEmpty

data class PostAnimalTypeDTO(
        @field:NotEmpty
        val animalType: String
)