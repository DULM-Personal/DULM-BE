package org.example.dulm.domain.user.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.example.dulm.domain.user.domain.User
import java.time.LocalDateTime

@Entity
@Table(name = "users")
class UserJpaEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, unique = true)
    val email: String,

    @Column(nullable = false)
    val password: String,

    @Column(nullable = false)
    val nickname: String,

    @Column(nullable = false)
    val createdAt: LocalDateTime,

    @Column(nullable = false)
    val updatedAt: LocalDateTime
) {
    fun toDomain() = User(
        id = id,
        email = email,
        password = password,
        nickname = nickname,
        createdAt = createdAt,
        updatedAt = updatedAt
    )

    companion object {
        fun from(user: User) = UserJpaEntity(
            id = user.id,
            email = user.email,
            password = user.password,
            nickname = user.nickname,
            createdAt = user.createdAt,
            updatedAt = user.updatedAt
        )
    }
}
