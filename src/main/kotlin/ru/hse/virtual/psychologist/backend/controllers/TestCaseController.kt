package ru.hse.virtual.psychologist.backend.controllers

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import ru.hse.virtual.psychologist.backend.data.entities.TestCase
import ru.hse.virtual.psychologist.backend.services.TestingSystemService

@RestController
@RequestMapping("/test-case")
class TestCaseController(private val testingSystemService: TestingSystemService) {
    @PutMapping("/refresh")
    fun refreshTestCases(): ResponseEntity<List<TestCase>> {
        return try {
            ResponseEntity.ok(testingSystemService.getTestCases())
        } catch (e: ResponseStatusException) {
            ResponseEntity.status(e.statusCode).build()
        }
    }
}