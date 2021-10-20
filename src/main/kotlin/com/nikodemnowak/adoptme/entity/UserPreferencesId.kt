package com.nikodemnowak.adoptme.entity

import java.io.Serializable
import java.util.*
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class UserPreferencesId(
    @Column(name = "userId") val userId: UUID?,
    @Column(name = "questionAnswersId") val questionAnswersId: UUID?
) : Serializable