package com.nikodemnowak.adoptme.mapper

import com.nikodemnowak.adoptme.dto.PostUserPreferencesDTO
import com.nikodemnowak.adoptme.entity.UserPreferences
import com.nikodemnowak.adoptme.repository.QuestionAnswersRepository
import com.nikodemnowak.adoptme.repository.UserRepository
import org.springframework.stereotype.Component
import java.lang.RuntimeException
import java.util.*

interface UserPreferencesMapper {
    fun toEntity(postUserPreferencesDTO: PostUserPreferencesDTO): UserPreferences
}

@Component
class UserPreferencesMapperImpl(
        private val userRepository: UserRepository,
        private val questionAnswersRepository: QuestionAnswersRepository
) : UserPreferencesMapper {
    override fun toEntity(postUserPreferencesDTO: PostUserPreferencesDTO): UserPreferences {
        with(postUserPreferencesDTO) {
            val user1 = userRepository.findUserById(UUID.fromString(user)) ?: throw RuntimeException("User with id $user not found")
            val questionAnswers1 = questionAnswersRepository.findQuestionAnswersById(UUID.fromString(questionAnswers)) ?: throw RuntimeException("Question answers with id $questionAnswers not found")
            return UserPreferences(user1, questionAnswers1, answer)
        }
    }
}