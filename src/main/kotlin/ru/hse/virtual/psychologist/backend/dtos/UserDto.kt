package ru.hse.virtual.psychologist.backend.dtos

import lombok.Builder
import lombok.Data

@Data
@Builder
data class UserDto (
    val name: String,
    val surname: String,
    val patronymic: String,
    val password: String,
    val email: String,
    val phoneNum: String,
    val age: Int,
)