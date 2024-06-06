package ru.hse.virtual.psychologist.backend.mappers

import org.mapstruct.Mapper
import ru.hse.virtual.psychologist.backend.data.entities.User
import ru.hse.virtual.psychologist.backend.dtos.UserListDto

@Mapper(componentModel = "spring")
interface UserEntityToUserListDto {
    fun map(user: User): UserListDto
}