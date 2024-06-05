package ru.hse.virtual.psychologist.backend.services

import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.server.ResponseStatusException
import ru.hse.virtual.psychologist.backend.data.entities.Result
import ru.hse.virtual.psychologist.backend.data.entities.TestCase
import ru.hse.virtual.psychologist.backend.data.entities.User
import ru.hse.virtual.psychologist.backend.data.repositories.ProblemRepository
import ru.hse.virtual.psychologist.backend.data.repositories.TestCaseRepository
import ru.hse.virtual.psychologist.backend.dtos.ResultApiDto
import ru.hse.virtual.psychologist.backend.exceptions.user.not.found.UserNotFoundException
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@Service
class TestingSystemService(
    private val testCaseRepository: TestCaseRepository,
    private val problemRepository: ProblemRepository,
    private val problemService: ProblemService,
    private val userService: UserService
) {
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

    fun getResultForUser(testCaseId: String): Result {
        val resultApiDtos = webClient.get()
            .uri("https://9ww9.ru/api/results/$testCaseId")
            .header(
                "Authorization",
                "Bearer " + System.getenv("TESTING_SYSTEM_KEY")
            )
            .retrieve()
            .bodyToFlux(ResultApiDto::class.java)
            .collectList()
            .block()
        val user = userService.findByEmail(
            SecurityContextHolder.getContext().authentication.name
        ) ?: throw UserNotFoundException()

        val resultDtoForCurrentUser = resultApiDtos?.find { it.email == user.email }
            ?: throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR)

        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val resultDate = resultDtoForCurrentUser.createdAt.split(",")[0]

        return Result(
            id = UUID.randomUUID(),
            problemId = problemService.getProblemIdByTestCaseId(testCaseId),
            nodes = resultDtoForCurrentUser.nodes,
            duration = resultDtoForCurrentUser.duration,
            createdAt = LocalDate.parse(resultDate, formatter)
        )
    }

}