package org.example.dulm.domain.mail.application.port.`in`

interface VerifyMailCodeUseCase {
    fun verify(
        mail: String,
        code: String,
    )
}
