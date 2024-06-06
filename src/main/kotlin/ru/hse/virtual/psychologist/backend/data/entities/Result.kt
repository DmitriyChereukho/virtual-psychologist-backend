package ru.hse.virtual.psychologist.backend.data.entities

import com.fasterxml.jackson.databind.JsonNode
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import lombok.Builder
import lombok.Data
import java.time.LocalDate
import java.util.*

@Builder
@Data
@Entity
@Table(name = "results")
data class Result(
    @Column(name = "problem_id")
    val problemId: UUID,

    @Column(name = "created_at")
    val createdAt: LocalDate?,

    @Column(name = "duration")
    val duration: String?,

    @Column(name = "nodes")
    val nodes: String?,

    @Id
    @Column(name = "id")
    val id: UUID
)
