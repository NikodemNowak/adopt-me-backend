package com.nikodemnowak.adoptme

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AdoptMeApplication

fun main(args: Array<String>) {
    runApplication<AdoptMeApplication>(*args)
}
