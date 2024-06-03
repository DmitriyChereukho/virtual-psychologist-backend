package ru.hse.virtual.psychologist.backend.exceptions.email

import lombok.AllArgsConstructor
import lombok.Data
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class EmailExistsExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(EmailExistsException::class)
    protected fun handleEmailExistsException(): ResponseEntity<EmailExceptionJson> {
        return ResponseEntity(
            EmailExceptionJson("Conflict", "There already is an account registered on this email."), HttpStatus.CONFLICT
        )
    }

    @Data
    @AllArgsConstructor
    data class EmailExceptionJson(
        val error: String,
        val message: String
    )
}
