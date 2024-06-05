package ru.hse.virtual.psychologist.backend.dtos

import lombok.Builder
import lombok.Data
import java.util.*

@Data
@Builder
data class ResultDto(
    val name: String,
    val id: UUID
)