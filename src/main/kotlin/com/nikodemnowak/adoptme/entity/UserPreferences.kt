package com.nikodemnowak.adoptme.entity

import javax.persistence.*

@Entity
data class UserPreferences(
    @ManyToOne(fetch = FetchType.LAZY) @MapsId("userId") val user: User,
    @ManyToOne(fetch = FetchType.LAZY) @MapsId("questionAnswersId") val questionAnswers: QuestionAnswers,
    var answer: String,
) {
    @EmbeddedId
    val id: UserPreferencesId = UserPreferencesId(user.id, questionAnswers.id)
}