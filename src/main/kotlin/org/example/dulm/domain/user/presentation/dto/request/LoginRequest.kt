package org.example.dulm.domain.user.presentation.dto.request

data class LoginRequest(
    val nickname: String,
    val password: String,
)
