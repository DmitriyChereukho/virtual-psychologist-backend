package ru.hse.virtual.psychologist.backend.data.entities

import jakarta.persistence.*
import lombok.Builder
import lombok.Data
import ru.hse.virtual.psychologist.backend.enums.Role
import java.util.*


@Data
@Entity
@Table(name = "users")
@Builder
data class User(
    @Column(name = "name")
    val name: String,

    @Column(name = "surname")
    val surname: String,

    @Column(name = "patronymic")
    val patronymic: String,

    @Column(name = "password")
    val password: String,

    @Column(unique = true, name = "email")
    val email: String,

    @Column(unique = true, name = "phone_num")
    val phoneNum: String,

    @Column(name = "age")
    val age: Int,

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    val role: Role,

    @Id
    @Column(name = "id")
    private val id: UUID
)