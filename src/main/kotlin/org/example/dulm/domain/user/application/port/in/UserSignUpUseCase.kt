package org.example.dulm.domain.user.application.port.`in`

import org.example.dulm.domain.user.presentation.dto.request.SignUpRequest
import org.example.dulm.global.security.jwt.dto.TokenResponse

interface UserSignUpUseCase {
    fun execute(request: SignUpRequest) : TokenResponse
}