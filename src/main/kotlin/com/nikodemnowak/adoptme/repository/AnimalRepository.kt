package com.nikodemnowak.adoptme.repository

import com.nikodemnowak.adoptme.entity.Animal
import com.nikodemnowak.adoptme.entity.AnimalType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AnimalRepository : JpaRepository<Animal, UUID> {
    fun findALlByExpiredIsFalse(): List<Animal>
    fun findAnimalByIdAndExpiredIsFalse(id: UUID): Animal?
    fun findAllByAnimalTypeAndExpiredIsFalse(animalType: AnimalType): List<Animal>?
}