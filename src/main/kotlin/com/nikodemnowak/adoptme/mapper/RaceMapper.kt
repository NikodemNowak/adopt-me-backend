package com.nikodemnowak.adoptme.mapper

import com.nikodemnowak.adoptme.dto.PostRaceDTO
import com.nikodemnowak.adoptme.entity.AnimalType
import com.nikodemnowak.adoptme.entity.Race
import com.nikodemnowak.adoptme.repository.AnimalTypeRepository
import org.springframework.stereotype.Component
import java.lang.RuntimeException

interface RaceMapper {
    fun toEntity(postRaceDTO: PostRaceDTO): Race
}

@Component
class RaceMapperImpl(
        private val animalTypeRepository: AnimalTypeRepository
) : RaceMapper {
    override fun toEntity(postRaceDTO: PostRaceDTO): Race {
        with(postRaceDTO) {
            val type: AnimalType = animalTypeRepository.findAnimalTypeByAnimalType(animalType)
                    ?: throw RuntimeException("Animal type $animalType not found")
            return Race(raceName, type)
        }
    }
}