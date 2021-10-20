package com.nikodemnowak.adoptme.mapper

import com.nikodemnowak.adoptme.dto.PostColorDTO
import com.nikodemnowak.adoptme.entity.Color
import org.springframework.stereotype.Component

interface ColorMapper {
    fun toEntity(postColorDTO: PostColorDTO): Color
}

@Component
class ColorMapperImpl : ColorMapper {
    override fun toEntity(postColorDTO: PostColorDTO): Color {
        with(postColorDTO) {
            return Color(colorName)
        }
    }
}