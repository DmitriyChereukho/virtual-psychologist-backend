package ru.hse.virtual.psychologist.backend.dtos

import com.fasterxml.jackson.databind.JsonNode
import lombok.Builder
import lombok.Data
import java.time.LocalDate

@Data
@Builder
data class ResultInfoDto(
    val name: String,
    val createdAt: LocalDate?,
    val duration: String?,
    val nodes: JsonNode?
)