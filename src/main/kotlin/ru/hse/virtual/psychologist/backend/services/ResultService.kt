package ru.hse.virtual.psychologist.backend.services

import org.springframework.stereotype.Service
import ru.hse.virtual.psychologist.backend.data.entities.Result
import ru.hse.virtual.psychologist.backend.dtos.ResultDto
import ru.hse.virtual.psychologist.backend.dtos.ResultInfoDto
import ru.hse.virtual.psychologist.backend.exceptions.results.NoResultsException
import java.util.*

@Service
class ResultService(
    private val testingSystemService: TestingSystemService,
    private val userService: UserService,
    private val problemService: ProblemService
) {
    fun refreshResultsForCurrentUser() {
        val user = userService.getThisSessionUser()

        val newResults: List<Result> = emptyList()

        for (result in user.results) {
            val newResult =
                testingSystemService.getResultForUser(problemService.getProblemById(result.problemId).testCaseId)
            newResults.plus(newResult)
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

        return ResultInfoDto(
            name = problem.name,
            //TODO На кой нам тут обработка нулль-полей? мы вроде бы и так раньше проверяем не пустой ли результат (пока сюда не делал эксепшена)
            createdAt = result.createdAt ?: throw IllegalArgumentException("Test has not been passed yet"),
            duration = result.duration ?: throw IllegalArgumentException("Test has not been passed yet"),
            nodes = result.nodes ?: throw IllegalArgumentException("Test has not been passed yet")
        )
    }
}