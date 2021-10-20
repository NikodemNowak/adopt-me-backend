package com.nikodemnowak.adoptme.repository

import com.nikodemnowak.adoptme.entity.Color
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ColorRepository : JpaRepository<Color, UUID> {
    fun findColorByColorName(colorName: String): Color?
    fun findColorById(id: UUID): Color?
}