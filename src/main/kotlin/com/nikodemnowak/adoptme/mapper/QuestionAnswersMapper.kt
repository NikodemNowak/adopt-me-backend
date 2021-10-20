package com.nikodemnowak.adoptme.mapper

import com.nikodemnowak.adoptme.dto.PostQuestionAnswersDTO
import com.nikodemnowak.adoptme.entity.QuestionAnswers
import org.springframework.stereotype.Component

interface QuestionAnswersMapper {
    fun toEntity(postQuestionAnswersDTO: PostQuestionAnswersDTO): QuestionAnswers
}

@Component
class QuestionAnswersMapperImpl : QuestionAnswersMapper {
    override fun toEntity(postQuestionAnswersDTO: PostQuestionAnswersDTO): QuestionAnswers {
        with(postQuestionAnswersDTO) {
            return QuestionAnswers(answer1, answer2, answer3, answer4, questionText)
        }
    }
}