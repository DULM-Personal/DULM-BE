package org.example.dulm.domain.mail.application.repository

interface MailCodeRepository {
    fun save(
        mail: String,
        code: String,
    )

    fun find(mail: String): String?

    fun delete(mail: String)

    fun verify(
        mail: String,
        code: String,
    ): Boolean
}
