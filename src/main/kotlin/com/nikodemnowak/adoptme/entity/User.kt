package com.nikodemnowak.adoptme.entity

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

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

    // TODO: Dodac date waznosci kodu otp (30 sek)

    var otpSendDate: Date? = null,
    var otp: String? = null,
    var accessToken: String? = null,
    var refreshToken: String? = null,

    @Enumerated(EnumType.STRING)
    var nextOnboardingStep: NextOnboardingStep = NextOnboardingStep.FILL_FORM,

    var profilePicture: String? = null

) : AbstractEntity()