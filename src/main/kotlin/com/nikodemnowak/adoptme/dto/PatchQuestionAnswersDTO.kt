package com.nikodemnowak.adoptme.dto

import java.util.*

data class PatchQuestionAnswersDTO(
        val id: String,
        val answer1: String,
        val answer2: String,
        val answer3: String,
        val answer4: String,
        val questionText: String
)