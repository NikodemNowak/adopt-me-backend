package com.nikodemnowak.adoptme.service

import com.nikodemnowak.adoptme.dto.PatchShelterDTO
import com.nikodemnowak.adoptme.dto.PostShelterDTO
import com.nikodemnowak.adoptme.dto.ShelterDTO
import com.nikodemnowak.adoptme.entity.Shelter
import com.nikodemnowak.adoptme.mapper.ShelterMapper
import com.nikodemnowak.adoptme.repository.ShelterRepository
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.util.*

interface ShelterService {
    fun findAll(city: String?): List<ShelterDTO>
    fun save(postShelterDTO: PostShelterDTO): ShelterDTO
    fun update(patchShelterDTO: PatchShelterDTO): ShelterDTO
    fun setShelterExpired(id: UUID)
}

@Service
class ShelterServiceImpl(
        private val shelterRepository: ShelterRepository,
        private val shelterMapper: ShelterMapper
) : ShelterService {
    override fun findAll(city: String?): List<ShelterDTO> {
        return if (city != null) {
            val shelters = shelterRepository.findAllByCityAndExpiredIsFalse(city)
                    ?: throw RuntimeException("Shelters in city $city not found")
            shelters.toDto()
        } else {
            shelterRepository.findAllByExpiredIsFalse().toDto()
        }
    }

    override fun save(postShelterDTO: PostShelterDTO): ShelterDTO {
        return shelterRepository.save(shelterMapper.toEntity(postShelterDTO)).toDto()
    }

    override fun update(patchShelterDTO: PatchShelterDTO): ShelterDTO {
        with(patchShelterDTO) {
            val shelter = shelterRepository.findShelterByIdAndExpiredIsFalse(id)
                    ?: throw RuntimeException("Shelter with id $id not found")
            shelterName.apply { shelter.shelterName = this }
            address.apply { shelter.address = this }
            city.apply { shelter.city = this }
            phoneNumber.apply { shelter.phoneNumber = this }
            workingHours.apply { shelter.workingHours = this }
            return shelterRepository.save(shelter).toDto()
        }
    }

    override fun setShelterExpired(id: UUID) {
        val shelter = shelterRepository.findShelterByIdAndExpiredIsFalse(id)
                ?: throw RuntimeException("Shelter with id $id not found")
        shelter.expired = true
        shelterRepository.save(shelter)
    }
}

fun Shelter.toDto(): ShelterDTO = ShelterDTO(id!!, shelterName, address, city, phoneNumber, workingHours)

fun List<Shelter>.toDto(): List<ShelterDTO> {
    return map { it.toDto() }
}