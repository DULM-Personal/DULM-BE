package org.example.dulm.domain.user.application.port.`in`

import org.example.dulm.domain.user.presentation.dto.response.UserResponse

interface UserUseCase {
    fun signUp(email : String, password : String, nickname : String) : UserResponse
    fun getUser(id : Long) : UserResponse
}