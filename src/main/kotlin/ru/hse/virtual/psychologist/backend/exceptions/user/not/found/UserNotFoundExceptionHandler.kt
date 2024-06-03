package ru.hse.virtual.psychologist.backend.exceptions.user.not.found

import lombok.AllArgsConstructor
import lombok.Data
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class UserNotFoundExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(UserNotFoundException::class)
    protected fun handleUserNotFoundException() : ResponseEntity<UserNotFoundJson> {
        return ResponseEntity(UserNotFoundJson(
            "Not found",
            "There is no user with this email"),
            HttpStatus.NOT_FOUND)
    }

    @Data
    @AllArgsConstructor
    data class UserNotFoundJson (
        val error : String,
        val message : String
    )
}