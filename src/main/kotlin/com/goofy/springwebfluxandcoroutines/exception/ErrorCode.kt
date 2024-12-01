package com.goofy.springwebfluxandcoroutines.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(val status: HttpStatus, val description: String) {
    /** Common Error Code */
    BAD_REQUEST_ERROR(HttpStatus.BAD_REQUEST, "bad request"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류, 관리자에게 문의하세요"),
    INVALID_INPUT_VALUE_ERROR(HttpStatus.BAD_REQUEST, "input is invalid value"),
    INVALID_TYPE_VALUE_ERROR(HttpStatus.BAD_REQUEST, "invalid type value"),
    METHOD_NOT_ALLOWED_ERROR(HttpStatus.METHOD_NOT_ALLOWED, "Method type is invalid"),
    INVALID_MEDIA_TYPE_ERROR(HttpStatus.BAD_REQUEST, "invalid media type"),
    COROUTINE_CANCELLATION_ERROR(HttpStatus.BAD_REQUEST, "coroutine cancellation error"),

    /** todo */
    NOT_FOUND_TODO_ERROR(HttpStatus.NOT_FOUND, "todo 정보를 찾을 수 없습니다."),
    ;
}
