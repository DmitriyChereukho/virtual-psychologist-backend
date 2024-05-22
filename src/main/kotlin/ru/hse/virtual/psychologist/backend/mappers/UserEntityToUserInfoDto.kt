package ru.hse.virtual.psychologist.backend.mappers

import org.mapstruct.Mapper
import ru.hse.virtual.psychologist.backend.data.entities.User
import ru.hse.virtual.psychologist.backend.dtos.UserInfoDto

@Mapper(componentModel = "spring")
interface UserEntityToUserInfoDto {
    fun map(user: User?) : UserInfoDto
}