package org.example.dulm.domain.user.presentation

import org.example.dulm.domain.user.application.user.UserService
import org.example.dulm.domain.user.presentation.dto.request.SignUpRequest
import org.example.dulm.domain.user.presentation.dto.response.UserResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController (
    private val userService: UserService
){

    @PostMapping("/signup")
    fun signup(@RequestBody request : SignUpRequest): UserResponse {
        val userResponse = userService.signUp(request.email, request.password, request.nickname)
        return userResponse
    }
}