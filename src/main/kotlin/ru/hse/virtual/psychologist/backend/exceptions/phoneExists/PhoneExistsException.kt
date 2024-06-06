package ru.hse.virtual.psychologist.backend.exceptions.phoneExists

class PhoneExistsException(val phone: String) : RuntimeException()
