package com.nikodemnowak.adoptme.dto

import javax.validation.constraints.NotEmpty

data class PostUserLoginDTO(
    @field:NotEmpty
    val phoneNumber: String
)