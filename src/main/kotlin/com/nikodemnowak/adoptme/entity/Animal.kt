package com.nikodemnowak.adoptme.entity

import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
data class Animal(
    @ManyToOne
    var animalType: AnimalType,
    @ManyToOne
    var race: Race,
    @ManyToOne
    var color: Color,
    @ManyToOne
    @JoinColumn(nullable = true)
    var privateOwner: PrivateOwner?,
    @ManyToOne
    @JoinColumn(nullable = true)
    var shelter: Shelter?,
    var age: Int,
    var name: String?
) : AbstractEntity()