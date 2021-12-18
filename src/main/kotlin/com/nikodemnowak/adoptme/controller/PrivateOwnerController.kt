package com.nikodemnowak.adoptme.controller

import com.nikodemnowak.adoptme.ApiMessage
import com.nikodemnowak.adoptme.dto.PatchPrivateOwnerDTO
import com.nikodemnowak.adoptme.dto.PostPrivateOwnerDTO
import com.nikodemnowak.adoptme.dto.PrivateOwnerDTO
import com.nikodemnowak.adoptme.service.PrivateOwnerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.*
import org.springframework.web.bind.annotation.*
import java.util.*

@CrossOrigin
@RestController
@RequestMapping("/api/privateOwner")
class PrivateOwnerController(
        private val privateOwnerService: PrivateOwnerService
) {
    @GetMapping
    fun getPrivateOwners(@RequestParam(required = false) city: String?): ResponseEntity<List<PrivateOwnerDTO>> {
        return ok(privateOwnerService.findAll(city))
    }

    @PostMapping
    fun addPrivateOwner(postPrivateOwnerDTO: PostPrivateOwnerDTO): ResponseEntity<PrivateOwnerDTO> {
        return ResponseEntity(privateOwnerService.save(postPrivateOwnerDTO), HttpStatus.CREATED)
    }

    @PatchMapping
    fun editPrivateOwner(patchPrivateOwnerDTO: PatchPrivateOwnerDTO): ResponseEntity<PrivateOwnerDTO> {
        return ResponseEntity(privateOwnerService.update(patchPrivateOwnerDTO), HttpStatus.CREATED)
    }

    @DeleteMapping("/{privateOwnerId}")
    fun deletePrivateOwner(@PathVariable privateOwnerId: UUID): ResponseEntity<ApiMessage> {
        privateOwnerService.setPrivateOwnerExpired(privateOwnerId)
        return ok(ApiMessage("Private owner removed"))
    }
}