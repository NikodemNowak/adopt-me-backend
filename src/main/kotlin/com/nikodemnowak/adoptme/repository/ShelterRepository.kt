package com.nikodemnowak.adoptme.repository

import com.nikodemnowak.adoptme.entity.Shelter
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ShelterRepository : JpaRepository<Shelter, UUID> {
    fun findShelterByIdAndExpiredIsFalse(id: UUID?): Shelter?
    fun findAllByCityAndExpiredIsFalse(city: String): List<Shelter>?
    fun findAllByExpiredIsFalse(): List<Shelter>
}