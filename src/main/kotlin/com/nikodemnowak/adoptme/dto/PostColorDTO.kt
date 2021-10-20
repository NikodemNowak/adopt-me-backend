package com.nikodemnowak.adoptme.dto

import javax.validation.constraints.NotEmpty

data class PostColorDTO(
        @field:NotEmpty
        val colorName: String
)