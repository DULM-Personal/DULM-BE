package org.example.dulm.application.user.port.`in`

import org.springframework.http.ResponseEntity

interface UserUseCase {
    fun signUp(email : String, password : String, nickname : String) : UserDto
    fun getUser(id : Long): UserDto
}