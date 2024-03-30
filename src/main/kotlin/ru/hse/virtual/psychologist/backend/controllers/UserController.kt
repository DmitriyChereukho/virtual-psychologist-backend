package ru.hse.virtual.psychologist.backend.controllers

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ru.hse.virtual.psychologist.backend.dtos.UserDto
import ru.hse.virtual.psychologist.backend.mappers.UserDtoToUserEntityMapper
import ru.hse.virtual.psychologist.backend.services.AuthenticationService
import ru.hse.virtual.psychologist.backend.services.UserService

@RestController
class UserController(private val userService: UserService, private val userDtoToUserEntityMapper: UserDtoToUserEntityMapper) {
    @PostMapping("/signup")
    fun signup(@RequestBody user: UserDto) : UserDto {
        return userDtoToUserEntityMapper.map(userService.createUser(userDtoToUserEntityMapper.map(user)))
    }
}