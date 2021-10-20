package com.nikodemnowak.adoptme.mapper

import com.nikodemnowak.adoptme.dto.PostShelterDTO
import com.nikodemnowak.adoptme.entity.Shelter
import org.springframework.stereotype.Component

interface ShelterMapper {
    fun toEntity(postShelterDTO: PostShelterDTO): Shelter
}

@Component
class ShelterMapperImpl : ShelterMapper {
    override fun toEntity(postShelterDTO: PostShelterDTO): Shelter {
        with(postShelterDTO) {
            return Shelter(shelterName, address, city, phoneNumber, workingHours)
        }
    }
}