package org.example.dulm.domain.user.service

import org.example.dulm.domain.user.application.port.`in`.UserLoginUseCase
import org.example.dulm.domain.user.application.port.out.repository.UserRepository
import org.example.dulm.domain.user.presentation.dto.request.LoginRequest
import org.example.dulm.global.error.exception.DulmException
import org.example.dulm.global.error.exception.ErrorCode
import org.example.dulm.global.security.jwt.JwtTokenProvider
import org.example.dulm.global.security.jwt.dto.TokenResponse
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserLoginService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenProvider: JwtTokenProvider,
) : UserLoginUseCase {
    override fun execute(request: LoginRequest): TokenResponse {
        val user =
            userRepository.findByNickname(request.nickname)
                ?: throw DulmException(ErrorCode.USER_NOT_FOUND)

        if (!passwordEncoder.matches(request.password, user.password)) {
            throw DulmException(ErrorCode.INVALID_PASSWORD)
        }

        val accessToken = jwtTokenProvider.createAccessToken(user.email)
        val refreshToken = jwtTokenProvider.createRefreshToken(user.email)

        return TokenResponse(
            accessToken = accessToken,
            refreshToken = refreshToken,
        )
    }
}
