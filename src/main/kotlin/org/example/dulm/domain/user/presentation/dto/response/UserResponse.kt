package org.example.dulm.domain.user.presentation.dto.response

import org.example.dulm.domain.user.domain.User

data class UserResponse (
    val id : Long,
    val email : String,
    val nickname : String,
) {
    companion object {
        fun from(user: User) = UserResponse(
            id = user.id!!,
            email = user.email,
            nickname = user.nickname
        )
    }
}