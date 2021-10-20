package com.nikodemnowak.adoptme.controller

import com.nikodemnowak.adoptme.ApiMessage
import com.nikodemnowak.adoptme.dto.PatchUserDTO
import com.nikodemnowak.adoptme.dto.PostUserDTO
import com.nikodemnowak.adoptme.dto.UserDTO
import com.nikodemnowak.adoptme.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.*
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@CrossOrigin
@RestController
@RequestMapping("/api/users")
class UserController(
        private val userService: UserService
) {
    @GetMapping
    fun getUsers(): ResponseEntity<List<UserDTO>> {
        return ok(userService.findAll())
    }

    @PatchMapping
    fun editUser(patchUserDTO: PatchUserDTO): ResponseEntity<UserDTO> {
        return ResponseEntity(userService.update(patchUserDTO), HttpStatus.CREATED)
    }

    @DeleteMapping("/{userId}")
    fun deleteUser(@PathVariable userId: UUID): ResponseEntity<ApiMessage> {
        userService.setUserExpired(userId)
        return ok(ApiMessage("User removed"))
    }
}