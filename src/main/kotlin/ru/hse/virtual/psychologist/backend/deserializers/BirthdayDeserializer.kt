package ru.hse.virtual.psychologist.backend.deserializers

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class BirthdayDeserializer : JsonDeserializer<LocalDate>() {
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    override fun deserialize(p: JsonParser, ctxt: DeserializationContext?): LocalDate {
        val date = p.text
        return LocalDate.parse(date, formatter)
    }
}