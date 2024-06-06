package ru.hse.virtual.psychologist.backend.services

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import ru.hse.virtual.psychologist.backend.data.entities.Result
import ru.hse.virtual.psychologist.backend.data.entities.User
import ru.hse.virtual.psychologist.backend.data.repositories.UserRepository
import ru.hse.virtual.psychologist.backend.dtos.UserInfoUpdateRequest
import ru.hse.virtual.psychologist.backend.dtos.UserInfoDto
import ru.hse.virtual.psychologist.backend.exceptions.email.EmailExistsException
import ru.hse.virtual.psychologist.backend.exceptions.phone.PhoneExistsException
import ru.hse.virtual.psychologist.backend.exceptions.userNotFound.UserNotFoundException
import ru.hse.virtual.psychologist.backend.mappers.UserEntityToUserInfoDtoImpl
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository,
    private val encoder: PasswordEncoder,
    private val userEntityToUserInfoDto: UserEntityToUserInfoDtoImpl,
) {
    fun findByEmail(email: String): User? {
        return userRepository.findAll().find { it.email == email }
    }

    fun findByPhoneNum(phoneNum: String): User? {
        return userRepository.findAll().find { it.phoneNum == phoneNum }
    }

    fun createUser(user: User): User {
        if (findByEmail(user.email) != null) throw EmailExistsException(user.email)

        if (findByPhoneNum(user.phoneNum) != null) throw PhoneExistsException(user.phoneNum)

        return userRepository.save(user.copy(password = encoder.encode(user.password)))
    }

    fun getThisSessionUser(): User {
        val user = findByEmail(
            SecurityContextHolder.getContext().authentication.name
        ) ?: throw UserNotFoundException()

        return user
    }

    fun updateUser(updRequest: UserInfoUpdateRequest) {
        val oldUser = getThisSessionUser()

        userRepository.save(
            oldUser.copy(
                name = updRequest.name,
                surname = updRequest.surname,
                patronymic = updRequest.patronymic,
                birthday = updRequest.birthday
            )
        )

        return
    }

    fun getInfo(): UserInfoDto {
        return userEntityToUserInfoDto.map(
            findByEmail(SecurityContextHolder.getContext().authentication.name)
        )
    }

    fun addProblem(id: UUID) {
        val user = getThisSessionUser()

        val updatedUser = user.copy(
            results = user.results + Result(
                problemId = id,
                nodes = null,
                id = UUID.randomUUID(),
                createdAt = null,
                duration = null
            )
        )

        userRepository.save(updatedUser)
    }

    fun updateResults(results: List<Result>) {
        val user = getThisSessionUser()

        val updatedUser = user.copy(results = results)
        userRepository.save(updatedUser)
    }
}