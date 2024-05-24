package ru.hse.virtual.psychologist.backend.exceptions.phone

import lombok.AllArgsConstructor
import lombok.Data
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class PhoneExistsExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(PhoneExistsException::class)
    protected fun handlePhoneExistsException() : ResponseEntity<PhoneExceptionJson> {
        return ResponseEntity(
            PhoneExceptionJson("Conflict", "There already is an account registered on this phone number."), HttpStatus.CONFLICT)
    }

    @Data
    @AllArgsConstructor
    data class PhoneExceptionJson(
        val error : String,
        val message : String
    )
}
