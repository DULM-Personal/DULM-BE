package org.example.dulm.global.security.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.example.dulm.global.error.exception.DulmException
import org.example.dulm.global.error.exception.ErrorCode
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

@Component
class JwtTokenProvider(
    private val jwtProperties: JwtProperties
) {

    private val secretKey: SecretKey =
        SecretKeySpec(
            jwtProperties.secretKey.toByteArray(),
            SignatureAlgorithm.HS256.jcaName
        )


    fun createAccessToken(email: String): String =
        createToken(email, jwtProperties.accessExp)

    fun createRefreshToken(email: String): String =
        createToken(email, jwtProperties.refreshExp)

    private fun createToken(email: String, expiration: Long): String {
        val now = Date()
        return Jwts.builder()
            .setSubject(email)
            .setIssuedAt(now)
            .setExpiration(Date(now.time + expiration))
            .signWith(secretKey)
            .compact()
    }

    fun validateToken(token: String): Boolean =
        try {
            getClaims(token)
            true
        } catch (e: Exception) {
            false
        }

    fun getEmail(token: String): String {
        return getClaims(token).subject
            ?: throw DulmException(ErrorCode.INVALID_TOKEN)
    }

    private fun getClaims(token: String): Claims =
        Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(token)
            .body
}
