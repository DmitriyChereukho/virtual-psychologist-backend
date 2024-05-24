package ru.hse.virtual.psychologist.backend.services

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.server.ResponseStatusException
import ru.hse.virtual.psychologist.backend.data.entities.TestCase
import ru.hse.virtual.psychologist.backend.data.repositories.TestCaseRepository

@Service
class TestingSystemService(private val testCaseRepository: TestCaseRepository) {
    private val webClient = WebClient.create()
    fun getTestCases(): List<TestCase> {
        val testCases = webClient.get()
            .uri("https://9ww9.ru/api/testCases")
            .header(
                "Authorization",
                "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6InVnSGZHOUYiLCJ0b2tlbiI6IkpQYkl1cm92dHQvSE5oY2xkN1REd0FWSk82UDRMb3BQQ3F0OHpXWVhzQ2hiOFdLOG9oVVBrUWdHOUUvRDdQWEQiLCJpYXQiOjE3MTU1MzkwMTB9.js6b_XCmv1zAJKmy12ahTM-9iRe6g9wSQw4cN656j74"
            )
            .retrieve()
            .bodyToFlux(TestCase::class.java)
            .collectList()
            .block()

        testCases?.forEach { testCaseRepository.save(it) }
            ?: throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR)

        return testCases
    }
}