package org.example.dulm.global.exception

import org.example.dulm.global.error.exception.DulmException
import org.example.dulm.global.error.exception.ErrorCode

object InvalidTokenException : DulmException(ErrorCode.INVALID_TOKEN)
