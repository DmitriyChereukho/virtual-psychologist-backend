package ru.hse.virtual.psychologist.backend.mappers

import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import ru.hse.virtual.psychologist.backend.data.entities.User
import ru.hse.virtual.psychologist.backend.dtos.UserDto

@Mapper(componentModel = "spring")
interface UserDtoToUserEntityMapper {
    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID())")
    @Mapping(target = "role", expression = "java(ru.hse.virtual.psychologist.backend.enums.Role.CLIENT)")
    @Mapping(target = "results", expression = "java(java.util.Collections.emptyList())")
    fun map(userDto: UserDto): User

    @InheritInverseConfiguration
    fun map(user: User): UserDto
}