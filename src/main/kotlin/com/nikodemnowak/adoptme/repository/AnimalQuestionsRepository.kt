package com.nikodemnowak.adoptme.repository

import com.nikodemnowak.adoptme.entity.AnimalQuestions
import com.nikodemnowak.adoptme.entity.AnimalType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AnimalQuestionsRepository : JpaRepository<AnimalQuestions, UUID> {
    fun findAnimalQuestionsById(id: UUID): AnimalQuestions?
    fun findAllByAnimalType(animalType: AnimalType): List<AnimalQuestions>
}