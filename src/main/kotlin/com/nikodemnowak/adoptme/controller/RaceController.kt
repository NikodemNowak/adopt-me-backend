package com.nikodemnowak.adoptme.controller

import com.nikodemnowak.adoptme.dto.RaceDTO
import com.nikodemnowak.adoptme.service.RaceService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/api/races")
class RaceController(
        private val raceService: RaceService
) {
    @GetMapping
    fun getRaces(@RequestParam(required = false) animalType: String?): ResponseEntity<List<RaceDTO>> {
        return ResponseEntity.ok(raceService.findAll(animalType))
    }

//    @PostMapping
//    fun addRace()
}