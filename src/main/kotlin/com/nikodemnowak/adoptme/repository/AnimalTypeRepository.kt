package com.nikodemnowak.adoptme.repository

import com.nikodemnowak.adoptme.entity.AnimalType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AnimalTypeRepository : JpaRepository<AnimalType, UUID> {
    fun findAnimalTypeByAnimalType(animalType: String): AnimalType?
    fun findAnimalTypeById(id: UUID): AnimalType?
}