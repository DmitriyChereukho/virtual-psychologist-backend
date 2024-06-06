package ru.hse.virtual.psychologist.backend.exceptions.testCases

import lombok.AllArgsConstructor
import lombok.Data
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class TestCaseNotFoundExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(TestCaseNotFoundException::class)
    protected fun handleTestCaseNotFoundException(ex: TestCaseNotFoundException): ResponseEntity<TestCaseNotFoundExceptionJson> {
        return ResponseEntity(
            TestCaseNotFoundExceptionJson(
                "Not found",
                "There is test case with name ${ex.problemName}"
            ),
            HttpStatus.NOT_FOUND
        )
    }

    @Data
    @AllArgsConstructor
    data class TestCaseNotFoundExceptionJson(
        val error: String,
        val message: String
    )
}