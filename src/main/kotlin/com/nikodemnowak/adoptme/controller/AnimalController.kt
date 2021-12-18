package com.nikodemnowak.adoptme.controller

import com.nikodemnowak.adoptme.ApiMessage
import com.nikodemnowak.adoptme.dto.AnimalDTO
import com.nikodemnowak.adoptme.dto.PatchAnimalDTO
import com.nikodemnowak.adoptme.dto.PostAnimalDTO
import com.nikodemnowak.adoptme.service.AnimalService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.*
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@CrossOrigin
@RestController
@RequestMapping("/api/animals")
class AnimalController(
        private val animalService: AnimalService
) {

    @GetMapping
    fun getAnimals(@RequestParam(required = false) animalType: String?): ResponseEntity<List<AnimalDTO>> {
        return ok(animalService.findAll(animalType))
    }

    @PostMapping
    fun addAnimal(@Valid @RequestBody postAnimalDTO: PostAnimalDTO): ResponseEntity<AnimalDTO> {
        return ResponseEntity(animalService.save(postAnimalDTO), HttpStatus.CREATED)
    }

    @PatchMapping
    fun editAnimal(patchAnimalDTO: PatchAnimalDTO): ResponseEntity<AnimalDTO> {
        return ResponseEntity(animalService.update(patchAnimalDTO), HttpStatus.CREATED)
    }

    @DeleteMapping("/{animalId}")
    fun deleteAnimal(@PathVariable animalId: UUID): ResponseEntity<ApiMessage> {
        animalService.setAnimalExpired(animalId)
        return ok(ApiMessage("Animal removed"))
    }
}