package com.nikodemnowak.adoptme.dto

import javax.validation.constraints.NotEmpty

data class PostShelterDTO(
        @field:NotEmpty
        val shelterName: String,
        val address: String,
        val city: String,
        val phoneNumber: String,
        val workingHours: String
)