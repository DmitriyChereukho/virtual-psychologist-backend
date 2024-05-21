package ru.hse.virtual.psychologist.backend.dtos

data class AuthenticationRequest(
    val email: String,
    val password: String
)