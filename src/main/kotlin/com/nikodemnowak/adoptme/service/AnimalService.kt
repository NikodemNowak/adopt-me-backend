package com.nikodemnowak.adoptme.service

import com.nikodemnowak.adoptme.dto.AnimalDTO
import com.nikodemnowak.adoptme.dto.PatchAnimalDTO
import com.nikodemnowak.adoptme.dto.PostAnimalDTO
import com.nikodemnowak.adoptme.entity.Animal
import com.nikodemnowak.adoptme.mapper.AnimalMapper
import com.nikodemnowak.adoptme.repository.*
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.util.*

interface AnimalService {
    fun findAll(animalType: String?): List<AnimalDTO>?
    fun save(postAnimalDTO: PostAnimalDTO): AnimalDTO
    fun update(patchAnimalDTO: PatchAnimalDTO): AnimalDTO
    fun setAnimalExpired(animalId: UUID)
}

@Service
class AnimalServiceImpl(
        private val animalRepository: AnimalRepository,
        private val animalTypeRepository: AnimalTypeRepository,
        private val raceRepository: RaceRepository,
        private val colorRepository: ColorRepository,
        private val privateOwnerRepository: PrivateOwnerRepository,
        private val shelterRepository: ShelterRepository,
        private val animalMapper: AnimalMapper
) : AnimalService {
    override fun findAll(animalType: String?): List<AnimalDTO>? {
        return if (animalType != null) {
            val type = animalTypeRepository.findAnimalTypeByAnimalType(animalType)
                    ?: throw RuntimeException("Animal type with name $animalType not found")
            animalRepository.findAllByAnimalTypeAndExpiredIsFalse(type)?.toDto()
        } else {
            animalRepository.findALlByExpiredIsFalse().toDto()
        }
    }

    override fun save(postAnimalDTO: PostAnimalDTO): AnimalDTO {
        return animalRepository.save(animalMapper.toEntity(postAnimalDTO)).toDto()
    }

    override fun update(patchAnimalDTO: PatchAnimalDTO): AnimalDTO {
        with(patchAnimalDTO) {
            val animal = animalRepository.findAnimalByIdAndExpiredIsFalse(id)
                    ?: throw RuntimeException("Animal with id $id not found")
            val type = animalTypeRepository.findAnimalTypeByAnimalType(animalType)
                    ?: throw RuntimeException("Animal type with name $animalType not found")
            val r = raceRepository.findRaceByRaceName(race) ?: throw RuntimeException("Race $race not found")
            val c = colorRepository.findColorByColorName(color)
                    ?: throw RuntimeException("Color with name $color not found")
            val owner = privateOwnerRepository.findPrivateOwnerByIdAndExpiredIsFalse(privateOwner)
                    ?: throw RuntimeException("Private owner with id $privateOwner not found")
            val s = shelterRepository.findShelterByIdAndExpiredIsFalse(shelter)
                    ?: throw RuntimeException("Shelter with id $shelter not found")
            type.apply { animal.animalType = this }
            r.apply { animal.race = this }
            c.apply { animal.color = this }
            owner.apply { animal.privateOwner = this }
            s.apply { animal.shelter = this }
            return animalRepository.save(animal).toDto()
        }
    }

    override fun setAnimalExpired(animalId: UUID) {
        val animal = animalRepository.findAnimalByIdAndExpiredIsFalse(animalId)
                ?: throw RuntimeException("Animal with id $animalId not found")
        animal.expired = true
        animalRepository.save(animal)
    }
}

fun Animal.toDto(): AnimalDTO = AnimalDTO(
        id!!,
        animalType.toString(),
        race.toString(),
        color.toString(),
        shelter?.id,
        privateOwner?.id,
        age,
        name
)

fun List<Animal>.toDto(): List<AnimalDTO> {
    return map { it.toDto() }
}