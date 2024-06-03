package ru.hse.virtual.psychologist.backend.dtos

data class AuthenticationResponse(
    val accessToken: String,
    val role: Int
)