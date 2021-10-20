package com.nikodemnowak.adoptme.dto

import com.nikodemnowak.adoptme.entity.UserPreferencesId
import java.util.*

data class PatchUserPreferencesDTO(
        val id: UserPreferencesId,
        val user: UUID,
        val questionAnswers: UUID,
        val answer: String
)