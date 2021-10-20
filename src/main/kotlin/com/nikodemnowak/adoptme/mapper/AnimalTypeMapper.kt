package com.nikodemnowak.adoptme.mapper

import com.nikodemnowak.adoptme.dto.PostAnimalTypeDTO
import com.nikodemnowak.adoptme.entity.AnimalType
import org.springframework.stereotype.Component

interface AnimalTypeMapper {
    fun toEntity(postAnimalTypeDTO: PostAnimalTypeDTO): AnimalType
}

@Component
class AnimalTypeMapperImpl : AnimalTypeMapper {
    override fun toEntity(postAnimalTypeDTO: PostAnimalTypeDTO): AnimalType {
        with(postAnimalTypeDTO) {
            return AnimalType(animalType)
        }
    }
}