package com.nikodemnowak.adoptme.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Pattern

data class PostUserDTO(
        @field:NotEmpty
        val firstName: String,
        val lastName: String,
        val city: String,

        @field:Pattern(regexp = "^\\+48\\d{9}\$")
        val phoneNumber: String,

        val email: String
)