package ru.hse.virtual.psychologist.backend.dtos

data class UserListDto (
    val name: String,
    val surname: String,
    val email: String,
    val phoneNum: String?,
)