package com.nikodemnowak.adoptme.dto

import java.util.*

data class ShelterDTO(
        val id: UUID,
        val shelterName: String,
        val address: String,
        val city: String,
        val phoneNumber: String,
        val workingHours: String
)