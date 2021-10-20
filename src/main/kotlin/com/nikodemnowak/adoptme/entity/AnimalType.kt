package com.nikodemnowak.adoptme.entity

import javax.persistence.Entity

@Entity
data class AnimalType(
    var animalType: String
) : AbstractEntity()