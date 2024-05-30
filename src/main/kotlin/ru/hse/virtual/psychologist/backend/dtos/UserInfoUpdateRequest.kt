package ru.hse.virtual.psychologist.backend.dtos

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import ru.hse.virtual.psychologist.backend.serialization.deserializers.BirthdayDeserializer
import ru.hse.virtual.psychologist.backend.serialization.serializers.BirthdaySerializer
import java.time.LocalDate

data class UserInfoUpdateRequest(
    val name: String,
    val surname: String,
    val patronymic: String,

    @JsonDeserialize(using = BirthdayDeserializer::class)
    @JsonSerialize(using = BirthdaySerializer::class)
    val birthday: LocalDate,
)