package ru.hse.virtual.psychologist.backend.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import ru.hse.virtual.psychologist.backend.dtos.UserDto
import ru.hse.virtual.psychologist.backend.mappers.UserDtoToUserEntityMapper
import ru.hse.virtual.psychologist.backend.services.UserService

@RestController
@CrossOrigin(origins = ["http://localhost:3000"], allowCredentials = "true")
class UserController(
    private val userService: UserService,
    private val userDtoToUserEntityMapper: UserDtoToUserEntityMapper
) {
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    fun signup(@RequestBody user: UserDto): ResponseEntity<UserDto> {
        try {
            val createdUser = userService.createUser(userDtoToUserEntityMapper.map(user))
            return ResponseEntity.ok(userDtoToUserEntityMapper.map(createdUser))
        } catch (e: ResponseStatusException) {
            return ResponseEntity.status(e.reason!!.toInt()).build()
        }
    }
}