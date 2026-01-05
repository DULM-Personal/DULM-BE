package org.example.dulm.domain.mail.service

import org.example.dulm.domain.mail.application.port.`in`.SendMailCodeUseCase
import org.example.dulm.domain.mail.application.port.out.MailSenderPort
import org.example.dulm.domain.mail.application.repository.MailCodeRepository
import org.springframework.stereotype.Service

@Service
class SendMailCodeService(
    private val mailSenderPort: MailSenderPort,
    private val mailCodeRepository: MailCodeRepository,
) : SendMailCodeUseCase {
    override fun send(mail: String) {
        val code = (100000..999999).random().toString()

        mailCodeRepository.save(mail, code)
        mailSenderPort.send(mail, code)
    }
}
