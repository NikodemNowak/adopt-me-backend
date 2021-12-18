package com.nikodemnowak.adoptme.controller

import com.nikodemnowak.adoptme.dto.ColorDTO
import com.nikodemnowak.adoptme.dto.PatchColorDTO
import com.nikodemnowak.adoptme.dto.PostColorDTO
import com.nikodemnowak.adoptme.service.ColorService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.*
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/api/colors")
class ColorController(
        private val colorService: ColorService
) {
    @GetMapping
    fun getColors(): ResponseEntity<List<ColorDTO>> {
        return ok(colorService.findAll())
    }

    @PostMapping
    fun addColor(postColorDTO: PostColorDTO): ResponseEntity<ColorDTO> {
        return ResponseEntity(colorService.save(postColorDTO), HttpStatus.CREATED)
    }

    @PatchMapping
    fun editColor(patchColorDTO: PatchColorDTO): ResponseEntity<ColorDTO> {
        return ResponseEntity(colorService.update(patchColorDTO), HttpStatus.CREATED)
    }
}