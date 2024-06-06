package ru.hse.virtual.psychologist.backend.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.hse.virtual.psychologist.backend.dtos.ResultDto
import ru.hse.virtual.psychologist.backend.dtos.ResultInfoDto
import ru.hse.virtual.psychologist.backend.services.ResultService
import java.util.*

@RestController
@RequestMapping("/results")
class ResultController(private val resultService: ResultService) {
    @PutMapping("/refresh")
    fun refreshResults() {
        resultService.refreshResultsForCurrentUser()
    }

    @GetMapping
    fun getResults(): List<ResultDto> {
        return resultService.getResultsForCurrentUser()
    }

    @GetMapping("/{id}")
    fun getResultById(@PathVariable id: UUID): ResultInfoDto {
        return resultService.getResultById(id)
    }
}