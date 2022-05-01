package com.nikodemnowak.adoptme.repository

import com.nikodemnowak.adoptme.entity.Animal
import com.nikodemnowak.adoptme.entity.AnimalType
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AnimalRepository : PagingAndSortingRepository<Animal, UUID> {
    fun findALlByExpiredIsFalse(pageable: Pageable): Page<Animal>
    fun findAnimalByIdAndExpiredIsFalse(id: UUID): Animal?
    fun findAllByAnimalTypeAndExpiredIsFalse(animalType: AnimalType, pageable: Pageable): Page<Animal>?
}