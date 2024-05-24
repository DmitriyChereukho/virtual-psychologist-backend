package ru.hse.virtual.psychologist.backend.dtos

import lombok.Builder
import lombok.Data

@Data
@Builder
data class ProblemDto(
    val name: String,
    val testCaseLink: String,
    val formLink: String,
)