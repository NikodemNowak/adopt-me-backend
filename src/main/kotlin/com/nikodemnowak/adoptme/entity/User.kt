package com.nikodemnowak.adoptme.entity

import java.util.*
import javax.persistence.Entity

@Entity
data class User(
        var firstName: String,
        var lastName: String,
        var phoneNumber: String,
        var email: String,
        var city: String,
        var pin: String?,
        var session: UUID?,
        var otp: String?,
        var accessToken: String?,
        var refreshToken: String?
) : AbstractEntity()