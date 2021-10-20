package com.nikodemnowak.adoptme.service

import com.nikodemnowak.adoptme.dto.PatchQuestionAnswersDTO
import com.nikodemnowak.adoptme.dto.PostQuestionAnswersDTO
import com.nikodemnowak.adoptme.dto.QuestionAnswersDTO
import com.nikodemnowak.adoptme.entity.QuestionAnswers
import com.nikodemnowak.adoptme.mapper.QuestionAnswersMapper
import com.nikodemnowak.adoptme.repository.QuestionAnswersRepository
import org.springframework.stereotype.Service
import java.lang.RuntimeException

interface QuestionAnswersService {
    fun findAll(): List<QuestionAnswersDTO>
    fun save(postQuestionAnswersDTO: PostQuestionAnswersDTO): QuestionAnswersDTO
    fun update(patchQuestionAnswersDTO: PatchQuestionAnswersDTO): QuestionAnswersDTO
}

@Service
class QuestionAnswersServiceImpl(
        private val questionAnswersRepository: QuestionAnswersRepository,
        private val questionAnswersMapper: QuestionAnswersMapper
) : QuestionAnswersService {
    override fun findAll(): List<QuestionAnswersDTO> {
        return questionAnswersRepository.findAll().toDto()
    }

    override fun save(postQuestionAnswersDTO: PostQuestionAnswersDTO): QuestionAnswersDTO {
        return questionAnswersRepository.save(questionAnswersMapper.toEntity(postQuestionAnswersDTO)).toDto()
    }

    override fun update(patchQuestionAnswersDTO: PatchQuestionAnswersDTO): QuestionAnswersDTO {
        with(patchQuestionAnswersDTO) {
            val qa = questionAnswersRepository.findQuestionAnswersById(id)
                    ?: throw RuntimeException("Question answers with id $id not found")
            answer1.apply { qa.answer1 = this }
            answer2.apply { qa.answer2 = this }
            answer3.apply { qa.answer3 = this }
            answer4.apply { qa.answer4 = this }
            questionText.apply { qa.questionText = this }
            return questionAnswersRepository.save(qa).toDto()
        }
    }
}

fun QuestionAnswers.toDto(): QuestionAnswersDTO = QuestionAnswersDTO(id!!, answer1, answer2, answer3, answer4, questionText)

fun List<QuestionAnswers>.toDto(): List<QuestionAnswersDTO> {
    return map { it.toDto() }
}