package com.nikodemnowak.adoptme.mapper

import com.nikodemnowak.adoptme.dto.PostAnimalDTO
import com.nikodemnowak.adoptme.entity.*
import com.nikodemnowak.adoptme.repository.*
import org.springframework.stereotype.Component
import java.lang.RuntimeException

interface AnimalMapper {
    fun toEntity(postAnimalDTO: PostAnimalDTO): Animal
}

@Component
class AnimalMapperImpl(
        private val animalTypeRepository: AnimalTypeRepository,
        private val raceRepository: RaceRepository,
        private val colorRepository: ColorRepository,
        private val privateOwnerRepository: PrivateOwnerRepository,
        private val shelterRepository: ShelterRepository
) : AnimalMapper {
    override fun toEntity(postAnimalDTO: PostAnimalDTO): Animal {
        with(postAnimalDTO) {
            val type: AnimalType = animalTypeRepository.findAnimalTypeByAnimalType(animalType)
                    ?: throw RuntimeException("Animal type with name $animalType not found")
            val race1: Race = raceRepository.findRaceByRaceName(race) ?: throw RuntimeException("Race $race not found")
            val color1: Color = colorRepository.findColorByColorName(color)
                    ?: throw RuntimeException("Color with name $color not found")
            val owner: PrivateOwner = privateOwnerRepository.findPrivateOwnerByIdAndExpiredIsFalse(privateOwner)
                    ?: throw RuntimeException("Private owner with id $privateOwner not found")
            val shelter1: Shelter = shelterRepository.findShelterByIdAndExpiredIsFalse(shelter)
                    ?: throw RuntimeException("Shelter with id $shelter not found")
            return Animal(type, race1, color1, owner, shelter1, age, name)
        }
    }
}