package ru.hse.virtual.psychologist.backend.exceptions.emailExists

class EmailExistsException(val email : String) : RuntimeException()
