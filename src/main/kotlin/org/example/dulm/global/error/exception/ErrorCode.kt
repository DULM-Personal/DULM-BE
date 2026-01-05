package org.example.dulm.global.error.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val status: HttpStatus,
    val message: String,
) {
    // USER
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다"),
    EMAIL_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "이미 존재하는 이메일입니다"),
    NICKNAME_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "이미 존재하는 닉네임입니다"),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 잘못되었습니다"),

    // EMAIL AUTH
    EMAIL_CODE_NOT_FOUND(HttpStatus.BAD_REQUEST, "이메일 인증 코드가 존재하지 않습니다"),
    INVALID_EMAIL_CODE(HttpStatus.BAD_REQUEST, "이메일 인증 코드가 올바르지 않습니다"),
    EMAIL_CODE_EXPIRED(HttpStatus.BAD_REQUEST, "이메일 인증 코드가 만료되었습니다"),
    EMAIL_NOT_VERIFIED(HttpStatus.UNAUTHORIZED, "이메일 인증이 완료되지 않았습니다"),

    // AUTH / JWT
    LOGIN_FAILED(HttpStatus.UNAUTHORIZED, "로그인에 실패했습니다"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다"),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "만료된 토큰입니다"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증이 필요합니다"),
    FORBIDDEN(HttpStatus.FORBIDDEN, "접근 권한이 없습니다"),

    // COMMON
    INVALID_INPUT(HttpStatus.BAD_REQUEST, "잘못된 입력값입니다"),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "요청한 리소스를 찾을 수 없습니다"),
    DUPLICATE_REQUEST(HttpStatus.CONFLICT, "중복된 요청입니다"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류가 발생했습니다"),
}
