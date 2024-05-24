package ru.hse.virtual.psychologist.backend.services

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service
import ru.hse.virtual.psychologist.backend.config.JwtProperties
import ru.hse.virtual.psychologist.backend.dtos.AuthenticationRequest
import ru.hse.virtual.psychologist.backend.dtos.AuthenticationResponse
import java.util.*

@Service
class AuthenticationService(
    private val authenticationManager: AuthenticationManager,
    private val userDetailsService: AuthUserDetailsService,
    private val jwtService: JwtService,
    private val jwtProperties: JwtProperties
) {
    fun authenticate(authRequest: AuthenticationRequest): AuthenticationResponse {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authRequest.email,
                authRequest.password
            )
        )

        val user = userDetailsService.loadUserByUsername(authRequest.email)
        val accessToken = jwtService.generate(
            userDetails = user,
            expirationDate = Date(System.currentTimeMillis() + jwtProperties.accessTokenExpiration)
        )
        return AuthenticationResponse(
            accessToken, when (user.authorities.first().authority) {
                "ROLE_ADMIN" -> 5150
                "ROLE_CLIENT" -> 2001
                else -> 0
            }
        )
    }

}