package com.nikodemnowak.adoptme.entity

import javax.persistence.Column
import javax.persistence.Entity

@Entity
data class User(
        var firstName: String,
        var lastName: String,

        @Column(unique = true)
        var phoneNumber: String,

        @Column(unique = true)
        var email: String,

        var city: String,
        var pin: String? = null,
        var session: String? = null,
        var otp: String? = null,
        var accessToken: String? = null,
        var refreshToken: String? = null
) : AbstractEntity()