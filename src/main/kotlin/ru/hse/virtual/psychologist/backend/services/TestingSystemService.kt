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
                "Bearer " + System.getenv("TESTING_SYSTEM_KEY")
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