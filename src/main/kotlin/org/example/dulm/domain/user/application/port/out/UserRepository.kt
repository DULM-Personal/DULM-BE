package org.example.dulm.domain.user.application.port.out

import org.example.dulm.domain.user.domain.User

interface UserRepository {
    fun save(user: User): User
    fun findByEmail(email: String): User?
    fun findById(id: Long): User?
}