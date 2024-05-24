package ru.hse.virtual.psychologist.backend.controllers

import org.springframework.web.bind.annotation.*
import ru.hse.virtual.psychologist.backend.dtos.NewProblemDto
import ru.hse.virtual.psychologist.backend.dtos.ProblemDto
import ru.hse.virtual.psychologist.backend.services.ProblemService

@RestController
@RequestMapping("/problems")
class ProblemController(private val problemService: ProblemService) {
    @PostMapping
    fun addProblem(@RequestBody problem: NewProblemDto) {
        problemService.createProblem(problem)
    }

    @GetMapping
    fun getProblems(): List<ProblemDto> {
        return problemService.getProblems().map {
            ProblemDto(
                name = it.name,
                testCaseLink = it.testCaseLink,
                formLink = it.formLink
            )
        }
    }
}