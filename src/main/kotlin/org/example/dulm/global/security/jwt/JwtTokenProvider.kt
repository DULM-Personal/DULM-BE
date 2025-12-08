package org.example.dulm.global.security.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.example.dulm.global.error.exception.DulmException
import org.example.dulm.global.error.exception.ErrorCode
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtTokenProvider(
    private val jwtProperties: JwtProperties
) {

    private val secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.secretKey))

    // =============================
    //  토큰 생성
    // =============================
    fun createAccessToken(email: String, role: String): String {
        return createToken(
            email = email,
            role = role,
            expiration = jwtProperties.accessExp
        )
    }

    fun createRefreshToken(email: String, role: String): String {
        return createToken(
            email = email,
            role = role,
            expiration = jwtProperties.refreshExp
        )
    }

    private fun createToken(email: String, role: String, expiration: Long): String {
        val now = Date()
        val expireDate = Date(now.time + expiration)

        return Jwts.builder()
            .setSubject(email)
            .setIssuedAt(now)
            .setExpiration(expireDate)
            .claim("role", role)
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact()
    }

    // =============================
    //  토큰 검증
    // =============================
    fun validateToken(token: String): Boolean {
        return try {
            val claims = getClaims(token)
            !claims.expiration.before(Date())
        } catch (e: Exception) {
            throw DulmException(ErrorCode.INVALID_TOKEN)
        }
    }

    // =============================
    //  인증 객체 생성
    // =============================
    fun getAuthentication(token: String): Authentication {
        val claims = getClaims(token)
        val email = claims.subject
        val role = claims["role"].toString()

        val authorities = listOf(SimpleGrantedAuthority("ROLE_$role"))

        return UsernamePasswordAuthenticationToken(email, null, authorities)
    }

    // =============================
    //  토큰에서 Claim 추출
    // =============================
    fun getEmail(token: String): String =
        getClaims(token).subject

    fun getRole(token: String): String =
        getClaims(token)["role"].toString()

    // =============================
    //  claim 파싱 내부 로직
    // =============================
    private fun getClaims(token: String): Claims {
        return Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(token)
            .body
    }

    // =============================
    //  Authorization header → token 추출
    // =============================
    fun resolveToken(header: String?): String? {
        if (header != null && header.startsWith(jwtProperties.prefix)) {
            return header.substring(jwtProperties.prefix.length).trim()
        }
        return null
    }
}
