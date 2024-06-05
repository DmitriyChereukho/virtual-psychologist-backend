package ru.hse.virtual.psychologist.backend.dtos

import lombok.Builder
import lombok.Data

@Data
@Builder
data class ResultApiDto(
    val id: String,
    val fullName: String,
    val birthday: String,
    val age: Int,
    val email: String,
    val phoneNum: String,
    val address: String,
    val timeStart: String,
    val timeEnd: String,
    val createdAt: String,
    val duration: String,
    val status: Int,
    val score: Int,
    val nodes: String
)