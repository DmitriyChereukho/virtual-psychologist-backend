package ru.hse.virtual.psychologist.backend.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.hse.virtual.psychologist.backend.dtos.UserInfoDto
import ru.hse.virtual.psychologist.backend.services.UserInfoService

@RestController
@RequestMapping("/user")
class UserController(
    private val userInfoService: UserInfoService
) {
    @GetMapping("/info")
    fun getUserInfo() : UserInfoDto {
        return userInfoService.getInfo()
    }
}
