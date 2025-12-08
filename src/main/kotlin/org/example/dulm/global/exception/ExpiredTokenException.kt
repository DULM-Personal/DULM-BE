package org.example.dulm.global.exception

import org.example.dulm.global.error.exception.DulmException
import org.example.dulm.global.error.exception.ErrorCode

object ExpiredTokenException : DulmException(ErrorCode.EXPIRED_TOKEN)