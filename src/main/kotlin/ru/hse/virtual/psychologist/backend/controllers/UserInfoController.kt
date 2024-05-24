package ru.hse.virtual.psychologist.backend.controllers

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.hse.virtual.psychologist.backend.dtos.UserInfoDto
import ru.hse.virtual.psychologist.backend.services.UserInfoService

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = ["http://localhost:3000"], allowCredentials = "true")
class UserInfoController(
    private val userInfoService: UserInfoService
) {
    @GetMapping("/info")
    fun getUserInfo() : UserInfoDto {
        return userInfoService.getInfo()
    }
}
