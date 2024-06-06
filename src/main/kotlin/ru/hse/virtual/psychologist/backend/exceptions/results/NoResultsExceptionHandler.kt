package ru.hse.virtual.psychologist.backend.exceptions.results

import lombok.AllArgsConstructor
import lombok.Data
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class NoResultsExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(NoResultsException::class)
    protected fun handleNoResultsException(): ResponseEntity<NoResultsExceptionJson> {
        return ResponseEntity(
            NoResultsExceptionJson(
                "Not found",
                "There is no results for this user."
            ),
            HttpStatus.NOT_FOUND
        )
    }

    @Data
    @AllArgsConstructor
    data class NoResultsExceptionJson(
        val error: String,
        val message: String
    )
}
