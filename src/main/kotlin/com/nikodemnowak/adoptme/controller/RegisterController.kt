package com.nikodemnowak.adoptme.controller

import com.nikodemnowak.adoptme.dto.*
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
    fun addUser(@Valid @RequestBody postUserDTO: PostUserDTO): ResponseEntity<SessionDTO> {
        val session = userService.addUser(postUserDTO)
        userService.generateOtpCode(postUserDTO.phoneNumber)
        userService.sendOtpCode(postUserDTO.phoneNumber)
        return ResponseEntity(session, HttpStatus.OK)
//        return ResponseEntity("OK", HttpStatus.OK)
    }

    @PostMapping("/verifyOtp")
    fun verifyOtp(@Valid @RequestBody postVerifyOtpDTO: PostVerifyOtpDTO): ResponseEntity<SessionDTO> {
        return if (userService.verifyOtpCode(postVerifyOtpDTO)) {
            ResponseEntity(SessionDTO(userService.generateNewSession(postVerifyOtpDTO.session)), HttpStatus.OK)
        } else {
            ResponseEntity(SessionDTO("Otp invalid"), HttpStatus.BAD_REQUEST)
        }
    }

    @PostMapping("/setPin")
    fun setPin(@Valid @RequestBody postUserPinDTO: PostUserPinDTO): ResponseEntity<TokenDTO> {
        // zmiana next onboarding step na USER_ACTIVE_NO_ACTION_REQUIRED
        return ResponseEntity(userService.setPin(postUserPinDTO), HttpStatus.OK)
    }

    @GetMapping("/nextOnboardingStep")
    fun getNextOnboardingStep(@RequestHeader("session") session: String): ResponseEntity<String> {
        return ResponseEntity(userService.getNextOnboardingStep(session).toString(), HttpStatus.OK)
    }
}