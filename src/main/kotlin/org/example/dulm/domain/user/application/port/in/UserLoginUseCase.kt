package org.example.dulm.domain.user.application.port.`in`

import org.example.dulm.domain.user.presentation.dto.request.LoginRequest
import org.example.dulm.global.security.jwt.dto.TokenResponse

interface UserLoginUseCase {
    fun execute(request: LoginRequest): TokenResponse
}
