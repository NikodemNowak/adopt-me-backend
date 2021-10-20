package com.nikodemnowak.adoptme.repository

import com.nikodemnowak.adoptme.entity.AnimalType
import com.nikodemnowak.adoptme.entity.Race
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RaceRepository : JpaRepository<Race, UUID> {
    fun findRaceById(id: UUID): Race?
    fun findRaceByRaceName(raceName: String): Race?
    fun findAllByAnimalType(animalType: AnimalType): List<Race>
}