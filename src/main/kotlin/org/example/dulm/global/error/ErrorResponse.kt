package org.example.dulm.global.error

import org.example.dulm.global.error.exception.ErrorCode

data class ErrorResponse(
    val status: Int,
    val message: String
) {
    companion object {
        fun from(errorCode: ErrorCode): ErrorResponse =
            ErrorResponse(
                errorCode.status.value(),
                errorCode.message
            )
    }
}
