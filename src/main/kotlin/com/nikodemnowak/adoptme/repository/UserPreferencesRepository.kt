package com.nikodemnowak.adoptme.repository

import com.nikodemnowak.adoptme.entity.User
import com.nikodemnowak.adoptme.entity.UserPreferences
import com.nikodemnowak.adoptme.entity.UserPreferencesId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserPreferencesRepository : JpaRepository<UserPreferences, UserPreferencesId> {
    fun findAllByUser(user: User): List<UserPreferences>
    fun findUserPreferencesById(id: UserPreferencesId): UserPreferences?
}