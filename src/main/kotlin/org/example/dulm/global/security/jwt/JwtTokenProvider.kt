package org.example.dulm.global.security.jwt

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

@Component
class JwtTokenProvider(
    private val jwtProperties: JwtProperties
) {

    private val secretKey: SecretKey =
        SecretKeySpec(jwtProperties.secretKey.toByteArray(), SignatureAlgorithm.HS256.jcaName)


    fun createAccessToken(email: String): String =
        createToken(email, jwtProperties.accessExp)

    fun createRefreshToken(email: String): String =
        createToken(email, jwtProperties.refreshExp)


    private fun createToken(email: String, expiration: Long): String {
        val now = Date()
        val expireDate = Date(now.time + expiration)

        return Jwts.builder()
            .setSubject(email)
            .setIssuedAt(now)
            .setExpiration(expireDate)
            .signWith(secretKey)
            .compact()
    }
}

