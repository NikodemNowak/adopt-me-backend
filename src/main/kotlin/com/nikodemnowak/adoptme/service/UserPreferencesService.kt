package com.nikodemnowak.adoptme.service

import com.nikodemnowak.adoptme.dto.PatchUserPreferencesDTO
import com.nikodemnowak.adoptme.dto.PostUserPreferencesDTO
import com.nikodemnowak.adoptme.dto.UserPreferencesDTO
import com.nikodemnowak.adoptme.entity.UserPreferences
import com.nikodemnowak.adoptme.mapper.UserPreferencesMapper
import com.nikodemnowak.adoptme.repository.QuestionAnswersRepository
import com.nikodemnowak.adoptme.repository.UserPreferencesRepository
import com.nikodemnowak.adoptme.repository.UserRepository
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.util.*

interface UserPreferencesService {
    fun findAll(userId: String?): List<UserPreferencesDTO>
    fun save(postUserPreferencesDTO: PostUserPreferencesDTO): UserPreferencesDTO
    fun update(patchUserPreferencesDTO: PatchUserPreferencesDTO): UserPreferencesDTO
}

@Service
class UserPreferencesServiceImpl(
        private val userPreferencesRepository: UserPreferencesRepository,
        private val userRepository: UserRepository,
        private val userPreferencesMapper: UserPreferencesMapper
) : UserPreferencesService {
    override fun findAll(userId: String?): List<UserPreferencesDTO> {
        return if (userId != null) {
            val user = userRepository.findUserById(UUID.fromString(userId))
                    ?: throw RuntimeException("User with id $userId not found")
            userPreferencesRepository.findAllByUser(user).toDto()
        } else {
            userPreferencesRepository.findAll().toDto()
        }
    }

    override fun save(postUserPreferencesDTO: PostUserPreferencesDTO): UserPreferencesDTO {
        return userPreferencesRepository.save(userPreferencesMapper.toEntity(postUserPreferencesDTO)).toDto()
    }

    override fun update(patchUserPreferencesDTO: PatchUserPreferencesDTO): UserPreferencesDTO {
        with(patchUserPreferencesDTO) {
            val up = userPreferencesRepository.findUserPreferencesById(id) ?: throw RuntimeException("")
            answer.apply { up.answer }
            return userPreferencesRepository.save(up).toDto()
        }
    }
}

fun UserPreferences.toDto(): UserPreferencesDTO = UserPreferencesDTO(user.id.toString(), questionAnswers.id.toString(), answer)

fun List<UserPreferences>.toDto(): List<UserPreferencesDTO> {
    return map { it.toDto() }
}