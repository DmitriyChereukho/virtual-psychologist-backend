package ru.hse.virtual.psychologist.backend.controllers

import org.springframework.web.bind.annotation.*
import ru.hse.virtual.psychologist.backend.dtos.CatalogProblemDto
import ru.hse.virtual.psychologist.backend.dtos.NewProblemDto
import ru.hse.virtual.psychologist.backend.dtos.ProblemDto
import ru.hse.virtual.psychologist.backend.services.ProblemService
import java.util.UUID

@RestController
@RequestMapping("/problems")
class ProblemController(private val problemService: ProblemService) {
    @PostMapping
    fun addProblem(@RequestBody problem: NewProblemDto) {
        problemService.createProblem(problem)
    }

    @GetMapping
    fun getProblems(): List<CatalogProblemDto> {
        return problemService.getProblems().map {
            CatalogProblemDto(
                name = it.name,
                id = it.id
            )
        }
    }

    @GetMapping("/{id}")
    fun getProblem(@PathVariable id: UUID): ProblemDto {
        return problemService.getProblemDtoById(id)
    }

    @PutMapping("/{id}")
    fun buyProblem(@PathVariable id: UUID) {
        problemService.addProblemToUser(id)
    }
}