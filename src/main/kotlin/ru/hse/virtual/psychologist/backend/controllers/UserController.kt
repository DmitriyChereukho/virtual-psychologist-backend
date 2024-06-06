package ru.hse.virtual.psychologist.backend.controllers

import org.springframework.web.bind.annotation.*
import ru.hse.virtual.psychologist.backend.dtos.*
import ru.hse.virtual.psychologist.backend.mappers.UserEntityToUserListDtoImpl
import ru.hse.virtual.psychologist.backend.services.ProblemService
import ru.hse.virtual.psychologist.backend.services.UserService
import java.util.UUID

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService,
    private val problemService: ProblemService,
    private val userEntityToUserListDtoImpl: UserEntityToUserListDtoImpl
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
    fun getUserAdminInfo(@PathVariable id: UUID): Pair<UserListDto, List<ProblemDto>> {
        val user = userService.getUserById(id)
        val problems = mutableListOf<ProblemDto>()

        for (result in user.results)
            problems.add(problemService.getProblemDtoById(result.problemId))

        return Pair(userEntityToUserListDtoImpl.map(user), problems)
    }
}
