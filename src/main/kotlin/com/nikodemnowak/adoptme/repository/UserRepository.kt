package com.nikodemnowak.adoptme.repository

import com.nikodemnowak.adoptme.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, UUID> {
    fun findUserById(id: UUID): User?
    fun findUserByPhoneNumber(phoneNumber: String): User?
    fun findUserBySession(session: UUID): User?
}