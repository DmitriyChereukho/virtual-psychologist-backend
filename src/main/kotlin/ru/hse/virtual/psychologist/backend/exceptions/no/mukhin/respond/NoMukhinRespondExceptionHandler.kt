package ru.hse.virtual.psychologist.backend.exceptions.no.mukhin.respond

import lombok.AllArgsConstructor
import lombok.Data
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class NoMukhinRespondExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(NoMukhinRespondException::class)
    protected fun handleNoMukhinRespondException(): ResponseEntity<NoMukhinRespondExceptionJson> {
        return ResponseEntity(
            NoMukhinRespondExceptionJson(
                "Service unavailable",
                "No response from testing system."
            ),
            HttpStatus.SERVICE_UNAVAILABLE
        )
    }

    @Data
    @AllArgsConstructor
    data class NoMukhinRespondExceptionJson(
        val error: String,
        val message: String
    )
}
