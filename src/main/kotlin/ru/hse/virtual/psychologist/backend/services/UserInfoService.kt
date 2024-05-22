package ru.hse.virtual.psychologist.backend.services

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import ru.hse.virtual.psychologist.backend.dtos.UserInfoDto
import ru.hse.virtual.psychologist.backend.mappers.UserEntityToUserInfoDto

@Service
class UserInfoService(
    private val userService: UserService,
    private val userEntityToUserInfoDto: UserEntityToUserInfoDto, ) {
    fun getInfo() : UserInfoDto {
        return userEntityToUserInfoDto.map(
                userService.findByEmail(
                    SecurityContextHolder.getContext().authentication.name))
    }
}