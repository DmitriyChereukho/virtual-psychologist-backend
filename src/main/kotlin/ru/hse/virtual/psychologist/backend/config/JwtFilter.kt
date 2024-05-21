package ru.hse.virtual.psychologist.backend.config

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import ru.hse.virtual.psychologist.backend.services.AuthUserDetailsService
import ru.hse.virtual.psychologist.backend.services.JwtService

@Component
class JwtFilter(
    private val userDetailsService: AuthUserDetailsService, private val jwtService: JwtService
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain
    ) {
        val authorizationHeader: String? = request.getHeader("Authorization")

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response)
            return
        }

        val token = authorizationHeader.substringAfter("Bearer ")
        val email = jwtService.extractEmail(token)
        if (email != null && SecurityContextHolder.getContext().authentication == null) {
            val userDetails = userDetailsService.loadUserByUsername(email)

            if (jwtService.isValid(token, userDetails)) {
                updateContext(userDetails, request)
            }
            filterChain.doFilter(request, response)
        }
    }

    fun updateContext(userDetails: UserDetails, request: HttpServletRequest) {
        val authToken = UsernamePasswordAuthenticationToken(
            userDetails,
            null,
            userDetails.authorities
        )
        authToken.details = WebAuthenticationDetailsSource().buildDetails(request)

        SecurityContextHolder.getContext().authentication = authToken
    }

}