package org.example.dulm.global.error.exception

import org.example.dulm.global.error.exception.ErrorCode

open class DulmException (
    val errorCode : ErrorCode
) : RuntimeException()