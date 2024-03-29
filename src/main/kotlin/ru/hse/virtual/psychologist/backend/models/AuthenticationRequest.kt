package ru.hse.virtual.psychologist.backend.models

data class AuthenticationRequest(
    val email: String,
    val password: String
)