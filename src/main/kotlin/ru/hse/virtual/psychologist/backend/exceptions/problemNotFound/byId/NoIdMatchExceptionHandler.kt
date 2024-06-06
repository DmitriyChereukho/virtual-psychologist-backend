package ru.hse.virtual.psychologist.backend.exceptions.problemNotFound.byId

import lombok.AllArgsConstructor
import lombok.Data
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class NoIdMatchExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(NoIdMatchException::class)
    protected fun handleNoIdMatchException(ex: NoIdMatchException): ResponseEntity<NoIdMatchExceptionJson> {
        return ResponseEntity(
            NoIdMatchExceptionJson(
                "Not found",
                "There is no problem with id ${ex.id}."
            ),
            HttpStatus.NOT_FOUND
        )
    }

    @Data
    @AllArgsConstructor
    data class NoIdMatchExceptionJson(
        val error: String,
        val message: String
    )
}
