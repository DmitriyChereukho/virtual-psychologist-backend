package ru.hse.virtual.psychologist.backend.services

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.security.core.userdetails.User

@Service
class AuthUserDetailsService(private val userService: UserService) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        return userService.findByEmail(username)?.mapToUserDetails()
            ?: throw UsernameNotFoundException("User not found")
    }

    private fun ru.hse.virtual.psychologist.backend.data.entities.User.mapToUserDetails(): UserDetails {
        return User.builder()
            .username(this.email)
            .password(this.password)
            .roles(this.role.name)
            .build()
    }
}