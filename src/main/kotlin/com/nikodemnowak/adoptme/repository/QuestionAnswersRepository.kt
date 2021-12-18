package com.nikodemnowak.adoptme.repository

import com.nikodemnowak.adoptme.entity.QuestionAnswers
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface QuestionAnswersRepository : JpaRepository<QuestionAnswers, UUID> {
    fun findQuestionAnswersById(uuid: UUID): QuestionAnswers?
}