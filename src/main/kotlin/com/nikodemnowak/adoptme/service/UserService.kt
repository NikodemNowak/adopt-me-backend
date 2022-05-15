package com.nikodemnowak.adoptme.service

import com.nikodemnowak.adoptme.ApiException
import com.nikodemnowak.adoptme.dto.*
import com.nikodemnowak.adoptme.entity.NextOnboardingStep
import com.nikodemnowak.adoptme.entity.User
import com.nikodemnowak.adoptme.mapper.UserMapper
import com.nikodemnowak.adoptme.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*
import kotlin.RuntimeException

interface UserService {
    fun findAll(): List<UserDTO>
    fun findByAccessToken(accessToken: String): UserDTO
    fun loginUser(phoneNumber: String): SessionDTO
    fun addUser(postUserDTO: PostUserDTO): SessionDTO
    fun addUserTest(postUserDTO: PostUserDTO): UserDTO
    fun update(patchUserDTO: PatchUserDTO): UserDTO
    fun setUserExpired(userId: String)
    fun sendOtpCode(phoneNumber: String)
    fun generateOtpCode(phoneNumber: String): Date
    fun verifyOtpCode(postVerifyOtpDTO: PostVerifyOtpDTO): Boolean
    fun generateNewSession(session: String): String
    fun setPin(postUserPinDTO: PostUserPinDTO): TokenDTO
    fun verifyPin(postUserVerifyPinDTO: PostVerifyPinDTO): TokenDTO
    fun deleteBySession(session: String)
    fun getNextOnboardingStep(session: String): NextOnboardingStep
}

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper
) : UserService {
    override fun findAll(): List<UserDTO> {
        return userRepository.findAll().toDto()
    }

    override fun findByAccessToken(accessToken: String): UserDTO {
        return userRepository.findUserByAccessToken(accessToken)?.toDto()
            ?: throw RuntimeException("User with access token $accessToken not found")
    }

    override fun loginUser(phoneNumber: String): SessionDTO {
        val user = userRepository.findUserByPhoneNumber(phoneNumber)
            ?: throw RuntimeException("User with phone number $phoneNumber not found")
        user.session = UUID.randomUUID().toString()
        userRepository.save(user)
        return SessionDTO(user.session!!.toString())
    }

    override fun addUser(postUserDTO: PostUserDTO): SessionDTO {
        if (userRepository.existsByEmail(postUserDTO.email)) {
            throw ApiException(
                message = "User with this email already exists",
                errorCode = ApiException.EMAIL_TAKEN_ERROR_CODE
            )
        }
        if (userRepository.existsByPhoneNumber(postUserDTO.phoneNumber)) {
            throw ApiException(
                message = "User with this phone number already exists",
                errorCode = ApiException.PHONE_TAKEN_ERROR_CODE
            )
        }

        val user = userMapper.toEntity(postUserDTO)
        user.session = UUID.randomUUID().toString()
        user.nextOnboardingStep = NextOnboardingStep.OTP
        userRepository.save(user)
        return SessionDTO(user.session!!.toString())
    }

    override fun addUserTest(postUserDTO: PostUserDTO): UserDTO {
        val user = userMapper.toEntity(postUserDTO)
        userRepository.save(user)
        return user.toDto()
    }

    override fun update(patchUserDTO: PatchUserDTO): UserDTO {
        with(patchUserDTO) {
            val user =
                userRepository.findUserById(UUID.fromString(id)) ?: throw RuntimeException("User with id $id not found")
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

    override fun generateOtpCode(phoneNumber: String): Date {
        val user = userRepository.findUserByPhoneNumber(phoneNumber)
            ?: throw RuntimeException("User with phone number $phoneNumber not found")
        user.otp = buildString {
            for (i in 0 until 6) {
                append(kotlin.random.Random.nextInt(10))
            }
        }
        user.otpSendDate = Date()
        userRepository.save(user)
        return user.otpSendDate!!
    }

    override fun verifyOtpCode(postVerifyOtpDTO: PostVerifyOtpDTO): Boolean {
        val user = userRepository.findUserBySession(postVerifyOtpDTO.session)
            ?: throw RuntimeException("User with session ${postVerifyOtpDTO.session} not found")
        return (postVerifyOtpDTO.otpCode == user.otp).also { isValid ->
            if (isValid) {
                user.nextOnboardingStep = NextOnboardingStep.PIN
                userRepository.save(user)
            }
        }
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
//        user.session = UUID.randomUUID().toString()

        if (postUserPinDTO.pin.length != 4) {
            throw RuntimeException("Pin length should be 4")
        }

        user.pin = postUserPinDTO.pin
        user.refreshToken = UUID.randomUUID().toString()
        user.accessToken = UUID.randomUUID().toString()
        userRepository.save(user)

        return TokenDTO(user.accessToken!!, user.refreshToken!!)
    }

    override fun verifyPin(postUserVerifyPinDTO: PostVerifyPinDTO): TokenDTO {
        val user = userRepository.findUserBySession(postUserVerifyPinDTO.session)
            ?: throw RuntimeException("User with session ${postUserVerifyPinDTO.session} not found")
//        user.session = UUID.randomUUID().toString()

        if (postUserVerifyPinDTO.pin != user.pin) {
            throw RuntimeException("Invalid pin")
        }

        user.refreshToken = UUID.randomUUID().toString()
        user.accessToken = UUID.randomUUID().toString()
        userRepository.save(user)

        return TokenDTO(user.accessToken!!, user.refreshToken!!)
    }

    override fun deleteBySession(session: String) {
        userRepository.deleteBySession(session)
    }

    override fun getNextOnboardingStep(session: String): NextOnboardingStep {
        val user = userRepository.findUserBySession(session) ?: throw RuntimeException("No user found")
        return user.nextOnboardingStep
    }
}

fun User.toDto() = UserDTO(id, firstName, lastName, phoneNumber, email, city, profilePicture)
fun List<User>.toDto(): List<UserDTO> {
    return map { it.toDto() }
}