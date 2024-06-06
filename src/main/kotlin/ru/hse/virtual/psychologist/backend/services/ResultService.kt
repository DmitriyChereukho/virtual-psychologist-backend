package ru.hse.virtual.psychologist.backend.services

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Service
import ru.hse.virtual.psychologist.backend.data.entities.Result
import ru.hse.virtual.psychologist.backend.dtos.ResultDto
import ru.hse.virtual.psychologist.backend.dtos.ResultInfoDto
import ru.hse.virtual.psychologist.backend.exceptions.resultsNotFound.NoResultsException
import java.util.*

@Service
class ResultService(
    private val testingSystemService: TestingSystemService,
    private val userService: UserService,
    private val problemService: ProblemService
) {
    fun refreshResultsForCurrentUser() {
        val user = userService.getThisSessionUser()

        val newResults: MutableList<Result> = ArrayList()

        for (result in user.results) {
            val newResult =
                testingSystemService.getResultForUser(problemService.getProblemById(result.problemId).testCaseId)
            newResults.add(newResult)
        }

        userService.updateResults(newResults)
    }

    fun getResultsForCurrentUser(): List<ResultDto> {
        val user = userService.getThisSessionUser()

        return user.results.map {
            ResultDto(
                name = problemService.getProblemById(it.problemId).name,
                id = it.id
            )
        }
    }

    fun getResultById(id: UUID): ResultInfoDto {
        val user = userService.getThisSessionUser()

        val result = user.results.find { it.id == id }
            ?: throw NoResultsException()

        val problem = problemService.getProblemById(result.problemId)

        val objectMapper = ObjectMapper()

        return ResultInfoDto(
            name = problem.name,
            createdAt = result.createdAt,
            duration = result.duration,
            nodes = objectMapper.readTree(
                result.nodes?: "[]"
            )
        )
    }
}