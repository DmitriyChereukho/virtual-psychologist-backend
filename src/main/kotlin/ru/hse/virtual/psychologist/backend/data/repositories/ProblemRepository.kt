package ru.hse.virtual.psychologist.backend.data.repositories

import org.springframework.data.jpa.repository.JpaRepository
import ru.hse.virtual.psychologist.backend.data.entities.Problem
import java.util.*

interface ProblemRepository : JpaRepository<Problem, UUID>