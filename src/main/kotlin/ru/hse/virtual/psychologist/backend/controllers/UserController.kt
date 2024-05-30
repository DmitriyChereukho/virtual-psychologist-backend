package ru.hse.virtual.psychologist.backend.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.hse.virtual.psychologist.backend.dtos.UserInfoDto
import ru.hse.virtual.psychologist.backend.services.UserService

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService
) {
    @GetMapping("/info")
    fun getUserInfo() : UserInfoDto {
        return userService.getInfo()
    }

    // TODO Должен запрашивать информацию на обновление
    @GetMapping("/update")
    fun updateUserInfo() {
        return
    }
}
