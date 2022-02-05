package com.nikodemnowak.adoptme.config

import com.nikodemnowak.adoptme.dto.PostUserDTO
import com.nikodemnowak.adoptme.entity.NextOnboardingStep
import com.nikodemnowak.adoptme.entity.QuestionAnswers
import com.nikodemnowak.adoptme.entity.User
import com.nikodemnowak.adoptme.repository.QuestionAnswersRepository
import com.nikodemnowak.adoptme.repository.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DemoDataGenerator(
        private val userRepository: UserRepository,
        private val questionAnswersRepository: QuestionAnswersRepository
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        userRepository.save(User("a", "a", "+48123123123", "a@a.com", "Warsaw", nextOnboardingStep = NextOnboardingStep.FILL_FORM))
        questionAnswersRepository.save(QuestionAnswers("Yes", "No", "Not really", "I don't know", "Do you like to walk?"))
        questionAnswersRepository.save(QuestionAnswers("Yes", "No", "Not really", "I don't know", "Do you like to play with animals?"))
        questionAnswersRepository.save(QuestionAnswers("Yes", "No", "Not really", "I don't know", "Do you like to wake up early?"))
    }
}