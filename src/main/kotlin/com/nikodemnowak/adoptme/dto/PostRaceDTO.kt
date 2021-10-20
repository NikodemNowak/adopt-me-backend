package com.nikodemnowak.adoptme.dto

import javax.validation.constraints.NotEmpty

data class PostRaceDTO(
        @field:NotEmpty
        val raceName: String,
        val animalType: String
)