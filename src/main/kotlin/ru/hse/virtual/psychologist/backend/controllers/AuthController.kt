package ru.hse.virtual.psychologist.backend.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import ru.hse.virtual.psychologist.backend.models.AuthenticationRequest
import ru.hse.virtual.psychologist.backend.models.AuthenticationResponse
import ru.hse.virtual.psychologist.backend.services.AuthenticationService

@RestController
@RequestMapping("/login")
class AuthController(@Autowired private val authenticationService: AuthenticationService) {

    @PostMapping("")
    @ResponseBody
    fun authenticate(@RequestBody authRequest: AuthenticationRequest) : AuthenticationResponse {
        return authenticationService.authenticate(authRequest)
    }

}