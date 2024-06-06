package ru.hse.virtual.psychologist.backend.exceptions.problems.id

import java.util.UUID

class NoIdMatchException(val id : UUID) : RuntimeException()