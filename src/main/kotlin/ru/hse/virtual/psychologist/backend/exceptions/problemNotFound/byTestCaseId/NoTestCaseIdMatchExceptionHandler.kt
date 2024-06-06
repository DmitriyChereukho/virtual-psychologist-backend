package ru.hse.virtual.psychologist.backend.exceptions.problemNotFound.byTestCaseId

import lombok.AllArgsConstructor
import lombok.Data
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class NoTestCaseIdMatchExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(NoTestCaseIdMatchException::class)
    protected fun handleNoTestCaseIdMatchException(ex: NoTestCaseIdMatchException): ResponseEntity<NoTestCaseIdMatchExceptionJson> {
        return ResponseEntity(
            NoTestCaseIdMatchExceptionJson(
                "Not found",
                "There is no problem with testCaseId ${ex.testCaseId}."
            ),
            HttpStatus.NOT_FOUND
        )
    }

    @Data
    @AllArgsConstructor
    data class NoTestCaseIdMatchExceptionJson(
        val error: String,
        val message: String
    )
}
