package ru.hse.virtual.psychologist.backend.services

import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import ru.hse.virtual.psychologist.backend.data.entities.User
import ru.hse.virtual.psychologist.backend.data.repositories.UserRepository

@Service
class UserService(private val userRepository: UserRepository, private val encoder: PasswordEncoder) {
    fun findByEmail(email: String): User? {
        return userRepository.findAll().find { it.email == email }
    }

    fun createUser(user: User) : User {
        if(findByEmail(user.email) != null) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "User with email ${user.email} already exists")
        }
        return userRepository.save(user.copy(password = encoder.encode(user.password)))
    }
}