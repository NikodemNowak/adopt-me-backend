package com.nikodemnowak.adoptme.mapper

import com.nikodemnowak.adoptme.dto.PostUserDTO
import com.nikodemnowak.adoptme.entity.User
import org.springframework.stereotype.Component

interface UserMapper {
    fun toEntity(postUserDTO: PostUserDTO): User
}

@Component
class UserMapperImpl : UserMapper {
    override fun toEntity(postUserDTO: PostUserDTO): User {
        with(postUserDTO) {
            return User(firstName, lastName, phoneNumber, email, city, null, null, null, null, null)
        }
    }
}