package org.example.dulm.domain.user.domain

import java.time.LocalDateTime

data class User (
    val id : Long? = null,
    val email : String,
    val password : String,
    val nickname : String,
    val createdAt : LocalDateTime = LocalDateTime.now(),
    val updatedAt : LocalDateTime = LocalDateTime.now()
)