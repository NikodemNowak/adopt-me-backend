package com.nikodemnowak.adoptme.entity

import javax.persistence.Entity

@Entity
data class Color(
    var colorName: String
) : AbstractEntity()