package com.nikodemnowak.adoptme.entity

import javax.persistence.Entity
import javax.persistence.ManyToOne

@Entity
data class AnimalQuestions(
    @ManyToOne
    var animalType: AnimalType,
    @ManyToOne
    var question: QuestionAnswers,
    var number: Int
) : AbstractEntity()