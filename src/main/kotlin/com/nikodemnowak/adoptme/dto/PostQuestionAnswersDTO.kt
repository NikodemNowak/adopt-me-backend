package com.nikodemnowak.adoptme.dto

import javax.validation.constraints.NotEmpty

data class PostQuestionAnswersDTO(
        @field:NotEmpty
        val answer1: String,
        val answer2: String,
        val answer3: String,
        val answer4: String,
        val questionText: String
)