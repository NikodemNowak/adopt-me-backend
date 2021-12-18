package com.nikodemnowak.adoptme.controller

import com.nikodemnowak.adoptme.dto.PatchUserPreferencesDTO
import com.nikodemnowak.adoptme.dto.PostUserPreferencesDTO
import com.nikodemnowak.adoptme.dto.UserPreferencesDTO
import com.nikodemnowak.adoptme.service.UserPreferencesService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@CrossOrigin
@RestController
@RequestMapping("/api/userPreferences")
class UserPreferencesController(
        private val userPreferencesService: UserPreferencesService
) {
    @GetMapping
    fun getUserPreferences(@RequestParam(required = false) userId: String?): ResponseEntity<List<UserPreferencesDTO>> {
        return ResponseEntity.ok(userPreferencesService.findAll(userId))
    }

    @PostMapping
    fun addUserPreferences(@Valid @RequestBody postUserPreferencesDTO: PostUserPreferencesDTO): ResponseEntity<UserPreferencesDTO> {
        return ResponseEntity(userPreferencesService.save(postUserPreferencesDTO), HttpStatus.CREATED)
    }

    @PatchMapping
    fun editUserPreferences(@RequestBody patchUserPreferencesDTO: PatchUserPreferencesDTO): ResponseEntity<UserPreferencesDTO> {
        return ResponseEntity(userPreferencesService.update(patchUserPreferencesDTO), HttpStatus.CREATED)
    }
}