package org.example.dulm.domain.user.application

import org.example.dulm.domain.user.application.port.`in`.UserUseCase
import org.example.dulm.domain.user.application.port.out.UserRepository
import org.example.dulm.domain.user.domain.User
import org.example.dulm.domain.user.presentation.dto.response.UserResponse
import org.example.dulm.global.error.exception.DulmException
import org.example.dulm.global.error.exception.ErrorCode
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService (
    private val userRepository : UserRepository,
    private val passwordEncoder : PasswordEncoder
): UserUseCase {

    override fun signUp(email: String, password: String, nickname: String): UserResponse {
        if (userRepository.findByEmail(email) != null) {
            throw DulmException(ErrorCode.EMAIL_ALREADY_EXISTS)
        }

        val encryptedPassword = passwordEncoder.encode(password)

        val user = User(email = email, password = password, nickname = nickname)
        val savedUser = userRepository.save(user)

        return UserResponse.from(savedUser)
    }

    override fun getUser(id: Long): UserResponse {
        val user = userRepository.findById(id)
            ?: throw DulmException(ErrorCode.USER_NOT_FOUND)

        return UserResponse.from(user)
    }

}