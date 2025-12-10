package org.example.dulm.domain.user.service

import org.example.dulm.domain.user.application.port.`in`.UserSignUpUseCase
import org.example.dulm.domain.user.application.port.out.UserRepository
import org.example.dulm.domain.user.domain.User
import org.example.dulm.domain.user.entity.UserJpaEntity
import org.example.dulm.domain.user.presentation.dto.request.SignUpRequest
import org.example.dulm.global.error.exception.DulmException
import org.example.dulm.global.error.exception.ErrorCode
import org.example.dulm.global.security.jwt.JwtTokenProvider
import org.example.dulm.global.security.jwt.dto.TokenResponse
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class SignUpService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenProvider: JwtTokenProvider  // ★ 추가됨
) : UserSignUpUseCase {

    override fun execute(request: SignUpRequest): TokenResponse {
        // 1. 이메일 중복 확인
        if (userRepository.existsByEmail(request.email)) {
            throw DulmException(ErrorCode.EMAIL_ALREADY_EXISTS)
        }

        // 2. 비밀번호 암호화
        val encodedPassword = passwordEncoder.encode(request.password)

        // 3. 도메인 객체 생성
        val user = User(
            email = request.email,
            password = encodedPassword,
            nickname = request.nickname
        )

        // 4. DB 저장
        val saved = userRepository.save(UserJpaEntity.from(user))

        // 5. 토큰 생성 (email 기반)
        val access = jwtTokenProvider.createAccessToken(saved.email)
        val refresh = jwtTokenProvider.createRefreshToken(saved.email)

        // 6. 토큰 응답 반환 ★ 없어서 오류났던 부분
        return TokenResponse(
            accessToken = access,
            refreshToken = refresh
        )
    }
}
