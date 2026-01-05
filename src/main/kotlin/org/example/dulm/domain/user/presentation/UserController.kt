package org.example.dulm.domain.user.presentation

import org.example.dulm.domain.user.presentation.dto.request.LoginRequest
import org.example.dulm.domain.user.presentation.dto.request.SignUpRequest
import org.example.dulm.domain.user.service.UserLoginService
import org.example.dulm.domain.user.service.UserSignUpService
import org.example.dulm.global.security.jwt.dto.TokenResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class UserController(
    private val userSignUpService: UserSignUpService,
    private val userLoginService: UserLoginService,
) {
    @PostMapping("/signup")
    fun signup(
        @RequestBody request: SignUpRequest,
    ): ResponseEntity<TokenResponse> {
        return ResponseEntity.ok(userSignUpService.execute(request))
    }

    @PostMapping("/login")
    fun login(
        @RequestBody request: LoginRequest,
    ): ResponseEntity<TokenResponse> {
        return ResponseEntity.ok(userLoginService.execute(request))
    }
}
