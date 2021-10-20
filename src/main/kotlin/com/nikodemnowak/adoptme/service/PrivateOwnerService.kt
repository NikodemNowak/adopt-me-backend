package com.nikodemnowak.adoptme.service

import com.nikodemnowak.adoptme.dto.PatchPrivateOwnerDTO
import com.nikodemnowak.adoptme.dto.PostPrivateOwnerDTO
import com.nikodemnowak.adoptme.dto.PrivateOwnerDTO
import com.nikodemnowak.adoptme.entity.PrivateOwner
import com.nikodemnowak.adoptme.mapper.PrivateOwnerMapper
import com.nikodemnowak.adoptme.repository.PrivateOwnerRepository
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.util.*

interface PrivateOwnerService {
    fun findAll(city: String?): List<PrivateOwnerDTO>
    fun save(postPrivateOwnerDTO: PostPrivateOwnerDTO): PrivateOwnerDTO
    fun update(patchPrivateOwnerDTO: PatchPrivateOwnerDTO): PrivateOwnerDTO
    fun setPrivateOwnerExpired(id: UUID)
}

@Service
class PrivateOwnerServiceImpl(
        private val privateOwnerRepository: PrivateOwnerRepository,
        private val privateOwnerMapper: PrivateOwnerMapper
) : PrivateOwnerService {
    override fun findAll(city: String?): List<PrivateOwnerDTO> {
        return if (city != null) {
            val owners = privateOwnerRepository.findAllByCityAndExpiredIsFalse(city)
                    ?: throw RuntimeException("Private owners in city $city not found")
            owners.toDto()
        } else {
            val owners = privateOwnerRepository.findAllByExpiredIsFalse()
            owners.toDto()
        }
    }

    override fun save(postPrivateOwnerDTO: PostPrivateOwnerDTO): PrivateOwnerDTO {
        return privateOwnerRepository.save(privateOwnerMapper.toEntity(postPrivateOwnerDTO)).toDto()
    }

    override fun update(patchPrivateOwnerDTO: PatchPrivateOwnerDTO): PrivateOwnerDTO {
        with(patchPrivateOwnerDTO) {
            val owner = privateOwnerRepository.findPrivateOwnerByIdAndExpiredIsFalse(id)
                    ?: throw RuntimeException("Private owner with id $id not found")
            firstName.apply { owner.firstName = this }
            lastName.apply { owner.lastName = this }
            phoneNumber.apply { owner.phoneNumber = this }
            email.apply { owner.email = this }
            city.apply { owner.city = this }
            return privateOwnerRepository.save(owner).toDto()
        }
    }

    override fun setPrivateOwnerExpired(id: UUID) {
        val owner = privateOwnerRepository.findPrivateOwnerByIdAndExpiredIsFalse(id)
                ?: throw RuntimeException("Private owner with id $id not found")
        owner.expired = true
        privateOwnerRepository.save(owner)
    }
}

fun PrivateOwner.toDto(): PrivateOwnerDTO = PrivateOwnerDTO(id!!, firstName, lastName, phoneNumber, email, city)

fun List<PrivateOwner>.toDto(): List<PrivateOwnerDTO> {
    return map { it.toDto() }
}