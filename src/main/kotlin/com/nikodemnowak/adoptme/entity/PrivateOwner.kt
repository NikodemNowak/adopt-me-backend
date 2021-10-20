package com.nikodemnowak.adoptme.entity

import javax.persistence.Entity

@Entity
data class PrivateOwner(
    var firstName: String,
    var lastName: String,
    var phoneNumber: String,
    var email: String,
    var city: String
) : AbstractEntity()