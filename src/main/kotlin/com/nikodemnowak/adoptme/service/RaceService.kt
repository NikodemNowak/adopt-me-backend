package com.nikodemnowak.adoptme.service

import com.nikodemnowak.adoptme.dto.PatchRaceDTO
import com.nikodemnowak.adoptme.dto.PostRaceDTO
import com.nikodemnowak.adoptme.dto.RaceDTO
import com.nikodemnowak.adoptme.entity.Race
import com.nikodemnowak.adoptme.mapper.RaceMapper
import com.nikodemnowak.adoptme.repository.AnimalTypeRepository
import com.nikodemnowak.adoptme.repository.RaceRepository
import org.springframework.stereotype.Service
import kotlin.RuntimeException

interface RaceService {
    fun findAll(animalType: String?): List<RaceDTO>
    fun save(postRaceDTO: PostRaceDTO): RaceDTO
    fun update(patchRaceDTO: PatchRaceDTO): RaceDTO
}

@Service
class RaceServiceImpl(
        private val raceRepository: RaceRepository,
        private val animalTypeRepository: AnimalTypeRepository,
        private val raceMapper: RaceMapper
) : RaceService {
    override fun findAll(animalType: String?): List<RaceDTO> {
        return if (animalType != null) {
            val type = animalTypeRepository.findAnimalTypeByAnimalType(animalType)
                    ?: throw RuntimeException("Animal type $animalType not found")
            raceRepository.findAllByAnimalType(type).toDto()
        } else {
            raceRepository.findAll().toDto()
        }
    }

    override fun save(postRaceDTO: PostRaceDTO): RaceDTO {
        return raceRepository.save(raceMapper.toEntity(postRaceDTO)).toDto()
    }

    override fun update(patchRaceDTO: PatchRaceDTO): RaceDTO {
        with(patchRaceDTO) {
            val race = raceRepository.findRaceById(id) ?: throw RuntimeException("Race with id $id not found")
            val type = animalTypeRepository.findAnimalTypeByAnimalType(animalType)
                    ?: throw RuntimeException("Animal type $animalType not found")
            raceName.apply { race.raceName = this }
            type.apply { race.animalType = this }
            return raceRepository.save(race).toDto()
        }
    }
}

fun Race.toDto(): RaceDTO = RaceDTO(id!!, raceName, animalType.toString())

fun List<Race>.toDto(): List<RaceDTO> {
    return map { it.toDto() }
}