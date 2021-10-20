package com.nikodemnowak.adoptme.entity

import java.util.*
import javax.persistence.Entity
import javax.persistence.ManyToOne

@Entity
data class Race(
    var raceName: String,
    @ManyToOne
    var animalType: AnimalType
) : AbstractEntity()