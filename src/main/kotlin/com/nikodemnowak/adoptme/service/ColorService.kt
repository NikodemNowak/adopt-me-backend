package com.nikodemnowak.adoptme.service

import com.nikodemnowak.adoptme.dto.ColorDTO
import com.nikodemnowak.adoptme.dto.PatchColorDTO
import com.nikodemnowak.adoptme.dto.PostColorDTO
import com.nikodemnowak.adoptme.entity.Color
import com.nikodemnowak.adoptme.mapper.ColorMapper
import com.nikodemnowak.adoptme.repository.ColorRepository
import org.springframework.stereotype.Service
import java.lang.RuntimeException

interface ColorService {
    fun findAll(): List<ColorDTO>
    fun save(postColorDTO: PostColorDTO): ColorDTO
    fun update(patchColorDTO: PatchColorDTO): ColorDTO
}

@Service
class ColorServiceImpl(
        private val colorRepository: ColorRepository,
        private val colorMapper: ColorMapper
) : ColorService {
    override fun findAll(): List<ColorDTO> {
        return colorRepository.findAll().toDto()
    }

    override fun save(postColorDTO: PostColorDTO): ColorDTO {
        return colorRepository.save(colorMapper.toEntity(postColorDTO)).toDto()
    }

    override fun update(patchColorDTO: PatchColorDTO): ColorDTO {
        with(patchColorDTO) {
            val color = colorRepository.findColorById(id) ?: throw RuntimeException("Color with id $id not found")
            colorName.apply { color.colorName = this }
            return colorRepository.save(color).toDto()
        }
    }
}

fun Color.toDto(): ColorDTO = ColorDTO(id!!, colorName)

fun List<Color>.toDto(): List<ColorDTO> {
    return map { it.toDto() }
}