package com.nikodemnowak.adoptme.service

import com.nikodemnowak.adoptme.dto.*
import com.nikodemnowak.adoptme.entity.User
import com.nikodemnowak.adoptme.mapper.UserMapper
import com.nikodemnowak.adoptme.repository.UserRepository
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.util.*

interface UserService {
    fun findAll(): List<UserDTO>
    fun addUser(postUserDTO: PostUserDTO): String
    fun addUserTest(postUserDTO: PostUserDTO): UserDTO
    fun update(patchUserDTO: PatchUserDTO): UserDTO
    fun setUserExpired(userId: String)
    fun sendOtpCode(phoneNumber: String)
    fun generateOtpCode(phoneNumber: String)
    fun verifyOtpCode(postVerifyOtpDTO: PostVerifyOtpDTO): Boolean
    fun generateNewSession(session: String): String
    fun setPin(postUserPinDTO: PostUserPinDTO): TokenDTO
}

@Service
class UserServiceImpl(
        private val userRepository: UserRepository,
        private val userMapper: UserMapper
) : UserService {
    override fun findAll(): List<UserDTO> {
        return userRepository.findAll().toDto()
    }

    override fun addUser(postUserDTO: PostUserDTO): String {
        val user = userMapper.toEntity(postUserDTO)
        user.session = UUID.randomUUID().toString()
        userRepository.save(user)
        return user.session!!.toString()
    }

    override fun addUserTest(postUserDTO: PostUserDTO): UserDTO {
        val user = userMapper.toEntity(postUserDTO)
        userRepository.save(user)
        return user.toDto()
    }

    override fun update(patchUserDTO: PatchUserDTO): UserDTO {
        with(patchUserDTO) {
            val user = userRepository.findUserById(UUID.fromString(id)) ?: throw RuntimeException("User with id $id not found")
            firstName.apply { user.firstName = this }
            lastName.apply { user.lastName = this }
            phoneNumber.apply { user.phoneNumber = this }
            email.apply { user.email = this }
            city.apply { user.city = this }
            return userRepository.save(user).toDto()
        }
    }

    override fun setUserExpired(userId: String) {
        val user = userRepository.findUserById(UUID.fromString(userId))
                ?: throw RuntimeException("User with id $userId not found")
        user.expired = true
        userRepository.save(user)
    }

    override fun sendOtpCode(phoneNumber: String) {

    }

    override fun generateOtpCode(phoneNumber: String) {
        val user = userRepository.findUserByPhoneNumber(phoneNumber)
                ?: throw RuntimeException("User with phone number $phoneNumber not found")
//        user.otp = Random.nextInt(from = 1, until = 9999).toString()
        user.otp = "123456"
        userRepository.save(user)
    }

    override fun verifyOtpCode(postVerifyOtpDTO: PostVerifyOtpDTO): Boolean {
        val user = userRepository.findUserBySession(postVerifyOtpDTO.session)
                ?: throw RuntimeException("User with session ${postVerifyOtpDTO.session} not found")
        return postVerifyOtpDTO.otpCode == user.otp
    }

    override fun generateNewSession(session: String): String {
        val user = userRepository.findUserBySession(session)
                ?: throw RuntimeException("User with session $session not found")
        user.session = UUID.randomUUID().toString()
        userRepository.save(user)
        return user.session!!
    }

    override fun setPin(postUserPinDTO: PostUserPinDTO): TokenDTO {
        val user = userRepository.findUserBySession(postUserPinDTO.session)
                ?: throw RuntimeException("User with session ${postUserPinDTO.session} not found")
        if (postUserPinDTO.pin.length == 4) user.pin = postUserPinDTO.pin else throw RuntimeException("Pin length should be 4")
        user.refreshToken = UUID.randomUUID().toString()
        user.accessToken = UUID.randomUUID().toString()
        return TokenDTO(user.accessToken!!, user.refreshToken!!)
    }
}

fun User.toDto() = UserDTO(id, firstName, lastName, phoneNumber, email, city)
fun List<User>.toDto(): List<UserDTO> {
    return map { it.toDto() }
}