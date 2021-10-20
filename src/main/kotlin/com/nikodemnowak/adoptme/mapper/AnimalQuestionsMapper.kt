package com.nikodemnowak.adoptme.mapper

import com.nikodemnowak.adoptme.dto.PostAnimalQuestionsDTO
import com.nikodemnowak.adoptme.entity.AnimalQuestions
import com.nikodemnowak.adoptme.repository.AnimalTypeRepository
import com.nikodemnowak.adoptme.repository.QuestionAnswersRepository
import org.springframework.stereotype.Component
import java.lang.RuntimeException

interface AnimalQuestionsMapper {
    fun toEntity(postAnimalQuestionsDTO: PostAnimalQuestionsDTO): AnimalQuestions
}

@Component
class AnimalQuestionsMapperImpl(
        private val animalTypeRepository: AnimalTypeRepository,
        private val questionAnswersRepository: QuestionAnswersRepository
) : AnimalQuestionsMapper {
    override fun toEntity(postAnimalQuestionsDTO: PostAnimalQuestionsDTO): AnimalQuestions {
        with(postAnimalQuestionsDTO) {
            val type = animalTypeRepository.findAnimalTypeByAnimalType(animalType)
                    ?: throw RuntimeException("Animal type $animalType not found")
            val que = questionAnswersRepository.findQuestionAnswersById(question)
                    ?: throw RuntimeException("Question answers with id $question not found")
            return AnimalQuestions(type, que, number)
        }
    }
}