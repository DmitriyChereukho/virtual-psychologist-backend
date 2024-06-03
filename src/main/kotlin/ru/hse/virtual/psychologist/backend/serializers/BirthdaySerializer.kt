package ru.hse.virtual.psychologist.backend.serializers

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class BirthdaySerializer : JsonSerializer<LocalDate>() {
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    override fun serialize(value: LocalDate, gen: JsonGenerator, serializers: SerializerProvider) {
        gen.writeString(value.format(formatter))
    }
}