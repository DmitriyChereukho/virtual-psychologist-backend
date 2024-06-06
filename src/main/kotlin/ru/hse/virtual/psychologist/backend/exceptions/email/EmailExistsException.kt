package ru.hse.virtual.psychologist.backend.exceptions.email

class EmailExistsException(val email : String) : RuntimeException()
