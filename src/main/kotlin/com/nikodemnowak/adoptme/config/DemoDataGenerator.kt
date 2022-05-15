package com.nikodemnowak.adoptme.config

import com.nikodemnowak.adoptme.dto.PostUserDTO
import com.nikodemnowak.adoptme.entity.*
import com.nikodemnowak.adoptme.repository.*
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DemoDataGenerator(
    private val userRepository: UserRepository,
    private val questionAnswersRepository: QuestionAnswersRepository,
    private val animalRepository: AnimalRepository,
    private val shelterRepository: ShelterRepository,
    private val privateOwnerRepository: PrivateOwnerRepository,
    private val colorRepository: ColorRepository,
    private val raceRepository: RaceRepository,
    private val animalTypeRepository: AnimalTypeRepository
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        userRepository.save(
            User(
                "a",
                "a",
                "+48123123123",
                "a@a.com",
                "Warsaw",
                "1357",
                profilePicture = "https://www2.deloitte.com/content/dam/Deloitte/nl/Images/promo_images/deloitte-nl-cm-digital-human-promo.jpg",
                nextOnboardingStep = NextOnboardingStep.FILL_FORM
            )
        )
        questionAnswersRepository.save(
            QuestionAnswers(
                "Yes",
                "No",
                "Not really",
                "I don't know",
                "Do you like to walk?"
            )
        )
        questionAnswersRepository.save(
            QuestionAnswers(
                "Yes",
                "No",
                "Not really",
                "I don't know",
                "Do you like to play with animals?"
            )
        )
        questionAnswersRepository.save(
            QuestionAnswers(
                "Yes",
                "No",
                "Not really",
                "I don't know",
                "Do you like to wake up early?"
            )
        )
        val dog = animalTypeRepository.save(AnimalType("Dog"))
        val cat = animalTypeRepository.save(AnimalType("Cat"))
        val labrador = raceRepository.save(Race("Labrador Retriever", dog))
        val pitbull = raceRepository.save(Race("Pitbull", dog))
        val bengal = raceRepository.save(Race("Bengal", cat))
        val british = raceRepository.save(Race("British Shorthair", cat))
        val black = colorRepository.save(Color("Black"))
        val white = colorRepository.save(Color("White"))
        val orange = colorRepository.save(Color("Orange"))
        val privateOwner1 =
            privateOwnerRepository.save(PrivateOwner("name", "lastname", "+48321321321", "b@b.com", "Warsaw"))
        val shelter1 = shelterRepository.save(Shelter("shelter1", "street 1", "Warsaw", "+48123321123", "8-16"))
        for (i in 0 until 100) {
            animalRepository.save(
                Animal(
                    dog,
                    labrador,
                    white,
                    privateOwner1,
                    null,
                    1,
                    "Scrappy",
                    "https://www.ardeaprints.com/p/172/dog-yellow-labrador-puppy-peaking-edge-8706167.jpg",
                    "Some interesting description"
                )
            )
            animalRepository.save(
                Animal(
                    cat,
                    bengal,
                    black,
                    null,
                    shelter1,
                    3,
                    "Kitty",
                    "https://images.pexels.com/photos/774731/pexels-photo-774731.jpeg",
                    "Some interesting description"
                )
            )
        }
    }
}