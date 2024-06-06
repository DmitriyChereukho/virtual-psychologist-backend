package ru.hse.virtual.psychologist.backend.exceptions.problemNotFound.byTestCaseId

class NoTestCaseIdMatchException(val testCaseId: String) : RuntimeException()