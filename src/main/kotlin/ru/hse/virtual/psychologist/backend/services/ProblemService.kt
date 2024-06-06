package ru.hse.virtual.psychologist.backend.services

import org.springframework.stereotype.Service
import ru.hse.virtual.psychologist.backend.data.entities.Problem
import ru.hse.virtual.psychologist.backend.data.repositories.ProblemRepository
import ru.hse.virtual.psychologist.backend.data.repositories.TestCaseRepository
import ru.hse.virtual.psychologist.backend.dtos.NewProblemDto
import ru.hse.virtual.psychologist.backend.dtos.ProblemDto
import java.util.*

@Service
class ProblemService(
    private val problemRepository: ProblemRepository,
    private val testCaseRepository: TestCaseRepository,
    private val userService: UserService
) {
    fun createProblem(problem: NewProblemDto) {
        //TODO No such test case exception
        val matchedTestCase = testCaseRepository.findAll().find { it.name == problem.testCaseName }
            ?: throw IllegalArgumentException("Test case with name ${problem.testCaseName} not found")

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
        //TODO No problem with id exception handling
        val problem =
            problemRepository.findById(id).orElseThrow { IllegalArgumentException("Problem with id $id not found") }

        return ProblemDto(
            name = problem.name,
            description = problem.description,
            testCaseLink = problem.testCaseLink,
            formLink = problem.formLink
        )
    }

    fun getProblemById(id: UUID): Problem {
        //TODO No problem with id exception handling
        return problemRepository.findById(id).orElseThrow { IllegalArgumentException("Problem with id $id not found") }
    }

    fun getProblemIdByTestCaseId(testCaseId: String): UUID {
        //TODO No problem with such test case id exception handling
        return problemRepository.findAll().find { it.testCaseId == testCaseId }?.id
            ?: throw IllegalArgumentException("Problem with testCaseId $testCaseId not found")
    }

    fun addProblemToUser(id: UUID) {
        userService.addProblem(id)
    }
}