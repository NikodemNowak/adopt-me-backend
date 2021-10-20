package com.nikodemnowak.adoptme.entity

import javax.persistence.Entity

@Entity
data class QuestionAnswers(
    var answer1: String,
    var answer2: String,
    var answer3: String,
    var answer4: String,
    var questionText: String
) : AbstractEntity()