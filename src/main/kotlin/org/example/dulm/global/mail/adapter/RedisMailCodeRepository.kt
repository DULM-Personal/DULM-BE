package org.example.dulm.global.mail.adapter

import org.example.dulm.domain.mail.application.repository.MailCodeRepository
import org.example.dulm.global.error.exception.DulmException
import org.example.dulm.global.error.exception.ErrorCode
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Repository
import java.time.Duration

@Repository
class RedisMailCodeRepository(
    private val redisTemplate: StringRedisTemplate,
) : MailCodeRepository {
    override fun save(
        mail: String,
        code: String,
    ) {
        redisTemplate.opsForValue()
            .set(key(mail), code, Duration.ofMinutes(5))
    }

    override fun find(mail: String): String? = redisTemplate.opsForValue().get(key(mail))

    override fun delete(mail: String) {
        redisTemplate.delete(key(mail))
    }

    override fun verify(
        mail: String,
        code: String,
    ): Boolean {
        val savedCode =
            find(mail)
                ?: throw DulmException(ErrorCode.EMAIL_CODE_NOT_FOUND)

        if (savedCode != code) {
            throw DulmException(ErrorCode.INVALID_EMAIL_CODE)
        }

        return true
    }

    private fun key(email: String): String = "MAIL_CODE:$email"
}
