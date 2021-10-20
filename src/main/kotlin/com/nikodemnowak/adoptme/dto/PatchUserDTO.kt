package com.nikodemnowak.adoptme.dto

import java.util.*

data class PatchUserDTO(
        val id: UUID,
        val firstName: String,
        val lastName: String,
        val phoneNumber: String,
        val email: String,
        val city: String
)