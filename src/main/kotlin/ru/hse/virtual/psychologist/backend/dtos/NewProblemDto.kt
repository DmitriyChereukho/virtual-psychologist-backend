package ru.hse.virtual.psychologist.backend.dtos

import lombok.Builder
import lombok.Data

@Data
@Builder
data class NewProblemDto(
    val name: String,
    val testCaseName: String,
    val testCaseLink: String,
    val formLink: String,
)