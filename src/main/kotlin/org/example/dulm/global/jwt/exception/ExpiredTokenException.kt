package org.example.dulm.global.jwt.exception

import org.example.dulm.global.error.exception.DulmException
import org.example.dulm.global.error.exception.ErrorCode

object ExpiredTokenException : DulmException(ErrorCode.EXPIRED_TOKEN)