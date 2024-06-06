package ru.hse.virtual.psychologist.backend.exceptions.problems.testCaseId

class NoTestCaseIdMatchException(val testCaseId: String) : RuntimeException()