package org.example.dulm.domain.user.application.port.out.repository

import org.example.dulm.domain.user.domain.User
import org.example.dulm.domain.user.entity.UserJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserJpaEntity, Long> {
    fun save(user: User): User

    fun findByEmail(email: String): User?

    fun findByNickname(nickname: String): UserJpaEntity?

    fun existsByEmail(email: String): Boolean

    fun existsByNickname(nickname: String): Boolean
}
