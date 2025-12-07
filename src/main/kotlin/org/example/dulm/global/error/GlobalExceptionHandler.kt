package org.example.dulm.global.error

import org.example.dulm.global.error.exception.DulmException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(DulmException::class)
    fun handleException(e: DulmException): ResponseEntity<ErrorResponse> {
        val code = e.errorCode
        val body = ErrorResponse.from(code)

        return ResponseEntity
            .status(code.status)
            .body(body)
    }
}