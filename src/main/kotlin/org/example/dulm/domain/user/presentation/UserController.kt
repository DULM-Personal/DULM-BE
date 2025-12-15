package org.example.dulm.domain.user.presentation

import org.example.dulm.domain.user.service.UserSignUpService
import org.example.dulm.domain.user.presentation.dto.request.LoginRequest
import org.example.dulm.domain.user.presentation.dto.request.SignUpRequest
import org.example.dulm.domain.user.presentation.dto.response.UserResponse
import org.example.dulm.global.security.jwt.dto.TokenResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController (
    private val userSignUpService: UserSignUpService
){

    @PostMapping("/signup")
    fun signup(@RequestBody request : SignUpRequest): TokenResponse {
        return userSignUpService.execute(request)
    }
}