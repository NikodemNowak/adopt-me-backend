package com.nikodemnowak.adoptme.service

import com.nikodemnowak.adoptme.dto.AnimalTypeDTO
import com.nikodemnowak.adoptme.dto.PatchAnimalTypeDTO
import com.nikodemnowak.adoptme.dto.PostAnimalTypeDTO
import com.nikodemnowak.adoptme.entity.AnimalType
import com.nikodemnowak.adoptme.mapper.AnimalTypeMapper
import com.nikodemnowak.adoptme.repository.AnimalTypeRepository
import org.springframework.stereotype.Service
import java.lang.RuntimeException

interface AnimalTypeService {
    fun findAll(): List<AnimalTypeDTO>
    fun save(postAnimalTypeDTO: PostAnimalTypeDTO): AnimalTypeDTO
    fun update(patchAnimalTypeDTO: PatchAnimalTypeDTO): AnimalTypeDTO
}

@Service
class AnimalTypeServiceImpl(
        private val animalTypeRepository: AnimalTypeRepository,
        private val animalTypeMapper: AnimalTypeMapper
) : AnimalTypeService {
    override fun findAll(): List<AnimalTypeDTO> {
        return animalTypeRepository.findAll().toDto()
    }

    override fun save(postAnimalTypeDTO: PostAnimalTypeDTO): AnimalTypeDTO {
        return animalTypeRepository.save(animalTypeMapper.toEntity(postAnimalTypeDTO)).toDto()
    }

    override fun update(patchAnimalTypeDTO: PatchAnimalTypeDTO): AnimalTypeDTO {
        with(patchAnimalTypeDTO) {
            val type = animalTypeRepository.findAnimalTypeById(id)
                    ?: throw RuntimeException("Animal type with name $animalType not found")
            animalType.apply { type.animalType = this }
            return animalTypeRepository.save(type).toDto()
        }
    }
}

fun AnimalType.toDto(): AnimalTypeDTO = AnimalTypeDTO(id!!, animalType)

fun List<AnimalType>.toDto(): List<AnimalTypeDTO> {
    return map { it.toDto() }
}