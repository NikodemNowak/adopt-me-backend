package com.nikodemnowak.adoptme.service

import com.nikodemnowak.adoptme.dto.AnimalQuestionsDTO
import com.nikodemnowak.adoptme.dto.PatchAnimalQuestionsDTO
import com.nikodemnowak.adoptme.dto.PostAnimalQuestionsDTO
import com.nikodemnowak.adoptme.entity.AnimalQuestions
import com.nikodemnowak.adoptme.mapper.AnimalQuestionsMapper
import com.nikodemnowak.adoptme.repository.AnimalQuestionsRepository
import com.nikodemnowak.adoptme.repository.AnimalTypeRepository
import com.nikodemnowak.adoptme.repository.QuestionAnswersRepository
import org.springframework.stereotype.Service
import java.lang.RuntimeException

interface AnimalQuestionsService {
    fun findAll(animalType: String?): List<AnimalQuestionsDTO>
    fun save(postAnimalQuestionsDTO: PostAnimalQuestionsDTO): AnimalQuestionsDTO
    fun update(patchAnimalQuestionsDTO: PatchAnimalQuestionsDTO): AnimalQuestionsDTO
}

@Service
class AnimalQuestionsServiceImpl(
        private val animalQuestionsRepository: AnimalQuestionsRepository,
        private val animalQuestionsMapper: AnimalQuestionsMapper,
        private val questionAnswersRepository: QuestionAnswersRepository,
        private val animalTypeRepository: AnimalTypeRepository
) : AnimalQuestionsService {
    override fun findAll(animalType: String?): List<AnimalQuestionsDTO> {
        return if (animalType != null) {
            val type = animalTypeRepository.findAnimalTypeByAnimalType(animalType)
                    ?: throw RuntimeException("Animal type with name $animalType not found")
            animalQuestionsRepository.findAllByAnimalType(type).toDto()
        } else {
            animalQuestionsRepository.findAll().toDto()
        }
    }

    override fun save(postAnimalQuestionsDTO: PostAnimalQuestionsDTO): AnimalQuestionsDTO {
        return animalQuestionsRepository.save(animalQuestionsMapper.toEntity(postAnimalQuestionsDTO)).toDto()
    }

    override fun update(patchAnimalQuestionsDTO: PatchAnimalQuestionsDTO): AnimalQuestionsDTO {
        with(patchAnimalQuestionsDTO) {
            val aq = animalQuestionsRepository.findAnimalQuestionsById(id)
                    ?: throw RuntimeException("Animal Questions with id $id not found")
            val type = animalTypeRepository.findAnimalTypeByAnimalType(animalType)
                    ?: throw RuntimeException("Animal type with name $animalType not found")
            val q = questionAnswersRepository.findQuestionAnswersById(question)
                    ?: throw RuntimeException("Question answers with id $question not found")
            type.apply { aq.animalType = this }
            q.apply { aq.question = this }
            number.apply { aq.number = this }
            return animalQuestionsRepository.save(aq).toDto()
        }
    }
}

fun AnimalQuestions.toDto(): AnimalQuestionsDTO = AnimalQuestionsDTO(id!!, animalType.animalType, question.questionText, number)

fun List<AnimalQuestions>.toDto(): List<AnimalQuestionsDTO> {
    return map { it.toDto() }
}