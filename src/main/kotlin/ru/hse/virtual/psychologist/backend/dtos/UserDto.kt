package ru.hse.virtual.psychologist.backend.dtos

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import lombok.Builder
import lombok.Data
import ru.hse.virtual.psychologist.backend.deserializers.BirthdayDeserializer
import ru.hse.virtual.psychologist.backend.serializers.BirthdaySerializer
import java.time.LocalDate

@Data
@Builder
data class UserDto (
    val name: String,
    val surname: String,
    val patronymic: String,
    val password: String,
    val email: String,
    val phoneNum: String,

    @JsonDeserialize(using = BirthdayDeserializer::class)
    @JsonSerialize(using = BirthdaySerializer::class)
    val birthday: LocalDate,
)