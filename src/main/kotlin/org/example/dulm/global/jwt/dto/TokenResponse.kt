package org.example.dulm.global.jwt.dto

data class TokenResponse (
    val accessToken : String,
    val refreshToken : String
)