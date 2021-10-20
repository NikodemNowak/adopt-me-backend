package com.nikodemnowak.adoptme.mapper

import com.nikodemnowak.adoptme.dto.PostPrivateOwnerDTO
import com.nikodemnowak.adoptme.entity.PrivateOwner
import org.springframework.stereotype.Component

interface PrivateOwnerMapper {
    fun toEntity(postPrivateOwnerDTO: PostPrivateOwnerDTO): PrivateOwner
}

@Component
class PrivateOwnerMapperImpl : PrivateOwnerMapper {
    override fun toEntity(postPrivateOwnerDTO: PostPrivateOwnerDTO): PrivateOwner {
        with(postPrivateOwnerDTO) {
            return PrivateOwner(firstName, lastName, phoneNumber, email, city)
        }
    }
}