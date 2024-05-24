package ru.hse.virtual.psychologist.backend.services

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import ru.hse.virtual.psychologist.backend.data.entities.User
import ru.hse.virtual.psychologist.backend.data.repositories.UserRepository
import ru.hse.virtual.psychologist.backend.exceptions.email.EmailExistsException
import ru.hse.virtual.psychologist.backend.exceptions.phone.PhoneExistsException

@Service
class UserService(private val userRepository: UserRepository, private val encoder: PasswordEncoder) {
    fun findByEmail(email: String): User? {
        return userRepository.findAll().find { it.email == email }
    }

    fun findByPhoneNum(phoneNum: String): User? {
        return userRepository.findAll().find { it.phoneNum == phoneNum }
    }

    fun createUser(user: User) : User {
        if(findByEmail(user.email) != null) throw EmailExistsException()

        if(findByPhoneNum(user.phoneNum) != null) throw PhoneExistsException()

        return userRepository.save(user.copy(password = encoder.encode(user.password)))
    }
}