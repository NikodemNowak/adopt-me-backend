package com.nikodemnowak.adoptme.entity

import javax.persistence.Entity

@Entity
data class Shelter(
    var shelterName: String,
    var address: String,
    var city: String,
    var phoneNumber: String,
    var workingHours: String
) : AbstractEntity()