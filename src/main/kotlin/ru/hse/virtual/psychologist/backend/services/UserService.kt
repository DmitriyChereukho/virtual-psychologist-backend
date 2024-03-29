package ru.hse.virtual.psychologist.backend.services

import org.springframework.stereotype.Service
import ru.hse.virtual.psychologist.backend.data.entities.User
import ru.hse.virtual.psychologist.backend.data.repositories.UserRepository

@Service
class UserService(private val userRepository: UserRepository) {
    fun findByEmail(email: String) : User?{
        return userRepository.findAll().find { it.email == email }
    }
}