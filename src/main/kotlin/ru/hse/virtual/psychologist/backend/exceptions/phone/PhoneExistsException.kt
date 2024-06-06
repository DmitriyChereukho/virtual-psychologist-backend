package ru.hse.virtual.psychologist.backend.exceptions.phone

class PhoneExistsException(val phone: String) : RuntimeException()
