package ru.hse.virtual.psychologist.backend.data.repositories

import org.springframework.data.jpa.repository.JpaRepository
import ru.hse.virtual.psychologist.backend.data.entities.User
import java.util.*

interface UserRepository : JpaRepository<User, UUID>