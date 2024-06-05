package ru.hse.virtual.psychologist.backend.data.entities

import jakarta.persistence.*
import lombok.Builder
import lombok.Data
import ru.hse.virtual.psychologist.backend.enums.Role
import java.time.LocalDate
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

    @Column(name = "birthday")
    val birthday: LocalDate,

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val results: List<Result>,

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    val role: Role,

    @Id
    @Column(name = "id")
    private val id: UUID
)