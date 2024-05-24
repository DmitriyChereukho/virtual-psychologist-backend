package ru.hse.virtual.psychologist.backend.data.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import lombok.Builder
import lombok.Data
import java.time.LocalDateTime

@Data
@Entity
@Table(name = "test_cases")
@Builder
data class TestCase(
    @Column(name = "name")
    val name: String,

    @Column(name = "tested")
    val tested: Int,

    @Column(name = "created_at")
    val createdAt: LocalDateTime,

    @Column(name = "last_update")
    val lastUpdate: LocalDateTime,

    @Id
    @Column(name = "id")
    val id: String
)