package ru.hse.virtual.psychologist.backend.data.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import lombok.Builder
import lombok.Data
import java.util.*
@Data
@Entity
@Table(name = "problems")
@Builder
data class Problem(
    @Column(name = "name")
    val name: String,

    @Column(name = "test_case_id")
    val testCaseId: String,

    @Column(name = "test_case_link")
    val testCaseLink: String,

    @Column(name = "form_link")
    val formLink: String,

    @Id
    @Column(name = "id")
    val id: UUID
)
