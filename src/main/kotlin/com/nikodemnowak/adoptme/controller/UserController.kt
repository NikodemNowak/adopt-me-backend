package com.nikodemnowak.adoptme.controller

import com.nikodemnowak.adoptme.ApiMessage
import com.nikodemnowak.adoptme.dto.PatchUserDTO
import com.nikodemnowak.adoptme.dto.PostUserDTO
import com.nikodemnowak.adoptme.dto.UserDTO
import com.nikodemnowak.adoptme.service.UserService
import org.springframework.data.jpa.repository.Query
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.*
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@CrossOrigin
@RestController
class UserController(
        private val userService: UserService
) {
    @GetMapping("/api/user")
    fun getUser(
        @RequestParam(name = "accessToken") accessToken: String
    ): ResponseEntity<UserDTO> {
        return ok(userService.findByAccessToken(accessToken))
    }

    @GetMapping("/api/users")
    fun getUsers(): ResponseEntity<List<UserDTO>> {
        return ok(userService.findAll())
    }

    @PostMapping("/api/users")
    fun addUser(@Valid @RequestBody postUserDTO: PostUserDTO): ResponseEntity<UserDTO> {
        return ResponseEntity(userService.addUserTest(postUserDTO), HttpStatus.CREATED)
    }

    @PatchMapping("/api/users")
    fun editUser(patchUserDTO: PatchUserDTO): ResponseEntity<UserDTO> {
        return ResponseEntity(userService.update(patchUserDTO), HttpStatus.CREATED)
    }

    @DeleteMapping("/api/users/{userId}")
    fun deleteUser(@PathVariable userId: String): ResponseEntity<ApiMessage> {
        userService.setUserExpired(userId)
        return ok(ApiMessage("User removed"))
    }

    @DeleteMapping("/api/users/{session}")
    fun deleteUserBySession(@PathVariable session: String): ResponseEntity<ApiMessage> {
        userService.deleteBySession(session)
        return ok(ApiMessage("User removed"))
    }
}