package com.nikodemnowak.adoptme.controller

import com.nikodemnowak.adoptme.dto.AnimalQuestionsDTO
import com.nikodemnowak.adoptme.dto.PatchAnimalQuestionsDTO
import com.nikodemnowak.adoptme.dto.PostAnimalQuestionsDTO
import com.nikodemnowak.adoptme.service.AnimalQuestionsService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/api/animalQuestions")
class AnimalQuestionsController(
        private val animalQuestionsService: AnimalQuestionsService
) {
    @GetMapping("/{animalType}")
    fun getAnimalQuestions(@PathVariable(required = false) animalType: String?): ResponseEntity<List<AnimalQuestionsDTO>> {
        return ResponseEntity.ok(animalQuestionsService.findAll(animalType))
    }

    @PostMapping
    fun addAnimalQuestions(postAnimalQuestionsDTO: PostAnimalQuestionsDTO): ResponseEntity<AnimalQuestionsDTO> {
        return ResponseEntity(animalQuestionsService.save(postAnimalQuestionsDTO), HttpStatus.CREATED)
    }

    @PatchMapping
    fun editAnimalQuestions(patchAnimalQuestionsDTO: PatchAnimalQuestionsDTO): ResponseEntity<AnimalQuestionsDTO> {
        return ResponseEntity(animalQuestionsService.update(patchAnimalQuestionsDTO), HttpStatus.CREATED)
    }
}