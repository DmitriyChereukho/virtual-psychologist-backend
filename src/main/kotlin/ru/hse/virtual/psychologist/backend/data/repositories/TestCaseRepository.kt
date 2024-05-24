package ru.hse.virtual.psychologist.backend.data.repositories

import org.springframework.data.jpa.repository.JpaRepository
import ru.hse.virtual.psychologist.backend.data.entities.TestCase

interface TestCaseRepository : JpaRepository<TestCase, String>