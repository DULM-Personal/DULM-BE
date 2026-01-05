package org.example.dulm.global.security.jwt.entity.repository

import org.example.dulm.global.security.jwt.entity.RefreshToken
import org.springframework.data.repository.CrudRepository

interface RefreshTokenRepository : CrudRepository<RefreshToken, String> {
    fun findByToken(token: String): RefreshToken?
}
