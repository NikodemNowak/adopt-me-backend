package com.nikodemnowak.adoptme.repository

import com.nikodemnowak.adoptme.entity.PrivateOwner
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PrivateOwnerRepository : JpaRepository<PrivateOwner, UUID> {
    fun findPrivateOwnerByIdAndExpiredIsFalse(id: UUID?): PrivateOwner?
    fun findAllByCityAndExpiredIsFalse(city: String): List<PrivateOwner>?
    fun findAllByExpiredIsFalse(): List<PrivateOwner>
}