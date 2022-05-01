package com.nikodemnowak.adoptme.mapper

import com.nikodemnowak.adoptme.dto.PostUserDTO
import com.nikodemnowak.adoptme.dto.UserDTO
import com.nikodemnowak.adoptme.entity.NextOnboardingStep
import com.nikodemnowak.adoptme.entity.User
import org.springframework.stereotype.Component

interface UserMapper {
    fun toEntity(postUserDTO: PostUserDTO): User
    fun toEntity(userDTO: UserDTO): User
}

@Component
class UserMapperImpl : UserMapper {
    override fun toEntity(postUserDTO: PostUserDTO): User {
        with(postUserDTO) {
            return User(firstName, lastName, phoneNumber, email, city, null, null, null, null, null)
        }
    }

    override fun toEntity(userDTO: UserDTO): User {
        with(userDTO) {
            return User(firstName, lastName, phoneNumber, email, city, null, null, null, null, null)
        }
    }
}