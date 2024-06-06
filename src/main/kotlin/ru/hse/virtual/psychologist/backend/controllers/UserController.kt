package ru.hse.virtual.psychologist.backend.controllers

import org.springframework.web.bind.annotation.*
import ru.hse.virtual.psychologist.backend.dtos.*
import ru.hse.virtual.psychologist.backend.mappers.UserEntityToUserInfoDtoImpl
import ru.hse.virtual.psychologist.backend.services.ProblemService
import ru.hse.virtual.psychologist.backend.services.UserService
import java.util.UUID

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService,
    private val problemService: ProblemService,
    private val userEntityToUserInfoDto: UserEntityToUserInfoDtoImpl
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

    @GetMapping("/list/{id}")
    fun getUserAdminInfo(@PathVariable id: UUID): Pair<UserInfoDto, List<String>> {
        val user = userService.getUserById(id)
        val problems = mutableListOf<String>()

        for (result in user.results)
            problems.add(problemService.getProblemDtoById(result.problemId).name)

        return Pair(userEntityToUserInfoDto.map(user), problems.sorted())
    }
}
