package ru.hse.virtual.psychologist.backend.controllers

import org.springframework.web.bind.annotation.*
import ru.hse.virtual.psychologist.backend.dtos.UserInfoUpdateRequest
import ru.hse.virtual.psychologist.backend.dtos.UserInfoDto
import ru.hse.virtual.psychologist.backend.dtos.UserListDto
import ru.hse.virtual.psychologist.backend.services.UserService

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService
) {
    @GetMapping("/info")
    fun getUserInfo(): UserInfoDto {
        return userService.getInfo()
    }

    @PutMapping
    fun updateUserInfo(@RequestBody updRequest: UserInfoUpdateRequest) {
        return userService.updateUser(updRequest)
    }

    @GetMapping("/list")
    fun getUsersList(): List<UserListDto> {
        return userService.getUsers()
    }
}
