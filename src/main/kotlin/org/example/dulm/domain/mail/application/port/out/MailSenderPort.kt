package org.example.dulm.domain.mail.application.port.out

interface MailSenderPort {
    fun send(
        mail: String,
        code: String,
    )
}
