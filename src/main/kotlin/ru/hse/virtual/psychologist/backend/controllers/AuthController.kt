package ru.hse.virtual.psychologist.backend.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import ru.hse.virtual.psychologist.backend.dtos.AuthenticationRequest
import ru.hse.virtual.psychologist.backend.dtos.AuthenticationResponse
import ru.hse.virtual.psychologist.backend.services.AuthenticationService

@RestController
@RequestMapping("/login")
class AuthController(@Autowired private val authenticationService: AuthenticationService) {

    @PostMapping("")
    @ResponseBody
    @CrossOrigin(origins = ["http://localhost:3000"], allowCredentials = "true")
    fun authenticate(@RequestBody authRequest: AuthenticationRequest) : AuthenticationResponse {
        return authenticationService.authenticate(authRequest)
    }

}