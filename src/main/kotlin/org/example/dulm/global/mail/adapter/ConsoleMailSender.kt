package org.example.dulm.global.mail.adapter

import org.example.dulm.domain.mail.application.port.out.MailSenderPort
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class ConsoleMailSender : MailSenderPort {
    private val log = LoggerFactory.getLogger(this::class.java)

    override fun send(
        mail: String,
        code: String,
    ) {
        log.info("[MAIL SEND] mail={}, code={}", mail, code)
    }
}
