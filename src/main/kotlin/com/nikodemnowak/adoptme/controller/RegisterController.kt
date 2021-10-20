package com.nikodemnowak.adoptme.controller

import com.nikodemnowak.adoptme.dto.PostUserDTO
import com.nikodemnowak.adoptme.dto.PostUserPinDTO
import com.nikodemnowak.adoptme.dto.PostVerifyOtpDTO
import com.nikodemnowak.adoptme.dto.TokenDTO
import com.nikodemnowak.adoptme.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/register")
class RegisterController(
        private val userService: UserService
) {
    @PostMapping("/addUser")
    fun addUser(@Valid @RequestBody postUserDTO: PostUserDTO): ResponseEntity<String> {
//        val session = userService.addUser(postUserDTO)
//        userService.generateOtpCode(postUserDTO.phoneNumber)
//        userService.sendOtpCode(postUserDTO.phoneNumber)
//        return ResponseEntity(session.toString(), HttpStatus.OK)
        return ResponseEntity("OK", HttpStatus.OK)
    }

    @PostMapping("/verifyOtp")
    fun verifyOtp(@Valid @RequestBody postVerifyOtpDTO: PostVerifyOtpDTO): ResponseEntity<UUID> {
        return if (userService.verifyOtpCode(postVerifyOtpDTO)) {
            ResponseEntity(userService.generateNewSession(postVerifyOtpDTO.session), HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

    @PostMapping("/setPin")
    fun setPin(@Valid @RequestBody postUserPinDTO: PostUserPinDTO): ResponseEntity<TokenDTO> {
        return ResponseEntity(userService.setPin(postUserPinDTO), HttpStatus.OK)
    }
}