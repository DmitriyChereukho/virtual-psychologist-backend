package ru.hse.virtual.psychologist.backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class VirtualPsychologistBackendApplication

fun main(args: Array<String>) {
    runApplication<VirtualPsychologistBackendApplication>(*args)
}
