package ru.hse.virtual.psychologist.backend.services

import org.springframework.stereotype.Service
import ru.hse.virtual.psychologist.backend.data.entities.Problem
import ru.hse.virtual.psychologist.backend.data.repositories.ProblemRepository
import ru.hse.virtual.psychologist.backend.data.repositories.TestCaseRepository
import ru.hse.virtual.psychologist.backend.dtos.NewProblemDto
import ru.hse.virtual.psychologist.backend.dtos.ProblemDto
import ru.hse.virtual.psychologist.backend.exceptions.problemNotFound.byId.NoIdMatchException
import ru.hse.virtual.psychologist.backend.exceptions.problemNotFound.byTestCaseId.NoTestCaseIdMatchException
import ru.hse.virtual.psychologist.backend.exceptions.testCaseNotFound.TestCaseNotFoundException
import java.util.*

@Service
class ProblemService(
    private val problemRepository: ProblemRepository,
    private val testCaseRepository: TestCaseRepository,
    private val userService: UserService
) {
    fun createProblem(problem: NewProblemDto) {
        val matchedTestCase = testCaseRepository.findAll().find { it.name == problem.testCaseName }
            ?: throw TestCaseNotFoundException(problem.testCaseName)

        val problemEntity = Problem(
            name = problem.name,
            description = problem.description,
            testCaseId = matchedTestCase.id,
            testCaseLink = problem.testCaseLink,
            formLink = problem.formLink,
            id = UUID.randomUUID()
        )

        problemRepository.save(problemEntity)
    }

    fun getProblems(): List<Problem> {
        return problemRepository.findAll()
    }

    fun getProblemDtoById(id: UUID): ProblemDto {
        val problem = problemRepository.findById(id).orElseThrow { NoIdMatchException(id) }

        return ProblemDto(
            name = problem.name,
            description = problem.description,
            testCaseLink = problem.testCaseLink,
            formLink = problem.formLink
        )
    }

    fun getProblemById(id: UUID): Problem {
        return problemRepository.findById(id).orElseThrow { NoIdMatchException(id) }
    }

    fun getProblemIdByTestCaseId(testCaseId: String): UUID {
        return problemRepository.findAll().find { it.testCaseId == testCaseId }?.id
            ?: throw NoTestCaseIdMatchException(testCaseId)
    }

    fun addProblemToUser(id: UUID) {
        userService.addProblem(id)
    }
}