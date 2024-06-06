package ru.hse.virtual.psychologist.backend.exceptions.problemNotFound.byId

import java.util.UUID

class NoIdMatchException(val id : UUID) : RuntimeException()