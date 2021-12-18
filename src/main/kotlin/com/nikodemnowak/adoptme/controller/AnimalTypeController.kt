package com.nikodemnowak.adoptme.controller

import com.nikodemnowak.adoptme.dto.AnimalTypeDTO
import com.nikodemnowak.adoptme.dto.PatchAnimalTypeDTO
import com.nikodemnowak.adoptme.dto.PostAnimalTypeDTO
import com.nikodemnowak.adoptme.service.AnimalTypeService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.*
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@CrossOrigin
@RestController
@RequestMapping("/api/animalType")
class AnimalTypeController(
        private val animalTypeService: AnimalTypeService
) {
    @GetMapping
    fun getAnimalTypes(): ResponseEntity<List<AnimalTypeDTO>> {
        return ok(animalTypeService.findAll())
    }

    @PostMapping
    fun addAnimalType(@Valid @RequestBody postAnimalTypeDTO: PostAnimalTypeDTO): ResponseEntity<AnimalTypeDTO> {
        return ResponseEntity(animalTypeService.save(postAnimalTypeDTO), HttpStatus.CREATED)
    }

    @PatchMapping
    fun editAnimalType(patchAnimalTypeDTO: PatchAnimalTypeDTO): ResponseEntity<AnimalTypeDTO> {
        return ResponseEntity(animalTypeService.update(patchAnimalTypeDTO), HttpStatus.CREATED)
    }
}