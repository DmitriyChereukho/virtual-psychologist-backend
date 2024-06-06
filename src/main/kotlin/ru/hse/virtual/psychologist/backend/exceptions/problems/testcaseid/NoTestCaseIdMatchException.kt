package ru.hse.virtual.psychologist.backend.exceptions.problems.testcaseid

class NoTestCaseIdMatchException(val testCaseId: String) : RuntimeException()