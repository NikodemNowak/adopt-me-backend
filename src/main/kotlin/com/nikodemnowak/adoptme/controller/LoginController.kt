package com.nikodemnowak.adoptme.controller

import com.nikodemnowak.adoptme.dto.*
import com.nikodemnowak.adoptme.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/login")
class LoginController(
    private val userService: UserService
) {
    @PostMapping("/sendOtp")
    fun sendOtp(@Valid @RequestBody postUserLoginDTO: PostUserLoginDTO): ResponseEntity<SessionDTO> {
        val session = userService.loginUser(postUserLoginDTO.phoneNumber)
        userService.generateOtpCode(postUserLoginDTO.phoneNumber)
        userService.sendOtpCode(postUserLoginDTO.phoneNumber)
        return ResponseEntity(session, HttpStatus.OK)
    }

    @PostMapping("/verifyOtp")
    fun verifyOtp(@Valid @RequestBody postVerifyOtpDTO: PostVerifyOtpDTO): ResponseEntity<SessionDTO> {
        return if (userService.verifyOtpCode(postVerifyOtpDTO)) {
            ResponseEntity(SessionDTO(userService.generateNewSession(postVerifyOtpDTO.session)), HttpStatus.OK)
        } else {
            ResponseEntity(SessionDTO(userService.generateNewSession(postVerifyOtpDTO.session)), HttpStatus.BAD_REQUEST)
        }
    }

    @PostMapping("/verifyPin")
    fun verifyPin(@Valid @RequestBody postVerifyPinDTO: PostVerifyPinDTO): ResponseEntity<TokenDTO> {
        return ResponseEntity(userService.verifyPin(postVerifyPinDTO), HttpStatus.OK)
    }
}