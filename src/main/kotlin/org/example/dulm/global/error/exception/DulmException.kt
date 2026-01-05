package org.example.dulm.global.error.exception

open class DulmException(
    val errorCode: ErrorCode,
) : RuntimeException()
