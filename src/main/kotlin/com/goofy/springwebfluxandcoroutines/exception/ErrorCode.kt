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
    QUERY_DSL_NOT_EXISTS_ERROR(HttpStatus.NOT_FOUND, "not found query dsl"),
    COROUTINE_CANCELLATION_ERROR(HttpStatus.BAD_REQUEST, "coroutine cancellation error"),
    FAIL_TO_TRANSACTION_TEMPLATE_EXECUTE_ERROR(HttpStatus.BAD_REQUEST, "fail to tx-templates execute error"),

    /** todo */
    NOT_FOUND_TODO_ERROR(HttpStatus.NOT_FOUND, "todo 정보를 찾을 수 없습니다."),

    /** redis pub-sub */
    NOT_FOUND_CHANNEL_ERROR(HttpStatus.NOT_FOUND, "channel not found"),
    NOT_FOUND_MESSAGE_ERROR(HttpStatus.NOT_FOUND, "message not found"),

    /** Auth Error Code */
    NOT_FOUND_TOKEN_ERROR(HttpStatus.UNAUTHORIZED, "not found token"),
    FAIL_TO_VERIFY_TOKEN_ERROR(HttpStatus.UNAUTHORIZED, "fail to verify token"),
    INVALID_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "유효한 엑세스 토큰이 아닙니다."),
    INVALID_REFRESH_TOKEN(HttpStatus.BAD_REQUEST, "유효한 리프레시 토큰이 아닙니다."),
    INVALID_TOKEN(HttpStatus.BAD_REQUEST, "유효한 토큰이 아닙니다."),
    NO_AUTHORITY_ERROR(HttpStatus.FORBIDDEN, "권한이 없습니다."),
    INVALID_OAUTH_PROVIDER(HttpStatus.BAD_REQUEST, "잘못된 oauth 벤더입니다."),
    DUPLICATED_USERNAME_ERROR(HttpStatus.BAD_REQUEST, "중복된 아이디 입니다."),

    /** OAuth Error Code */
    NOT_FOUND_OAUTH_PROVIDER_ERROR(HttpStatus.NOT_FOUND, "현재 미지원하는 제공자입니다."),

    /** User Error Code */
    NOT_FOUND_USER_ERROR(HttpStatus.NOT_FOUND, "유저 정보를 찾을 수 없습니다."),
    DUPLICATE_USER_NICKNAME_ERROR(HttpStatus.BAD_REQUEST, "중복된 유저 닉네임 입니다."),

    /** Group Error Code */
    DUPLICATE_GROUP_NAME_ERROR(HttpStatus.BAD_REQUEST, "중복된 그룹명입니다."),
    NOT_FOUND_GROUP_ERROR(HttpStatus.NOT_FOUND, "그룹 정보를 찾을 수 없습니다."),
    IMPOSSIBLE_TO_JOIN_GROUP_ERROR(HttpStatus.BAD_REQUEST, "그룹에 들어갈 수 없습니다."),
    ALREADY_JOIN_GROUP_ERROR(HttpStatus.BAD_REQUEST, "이미 그룹에 들어가 있습니다."),
    NOT_FOUND_JOIN_CODE_ERROR(HttpStatus.BAD_REQUEST, "비밀 그룹의 경우, 비밀번호가 필수 입니다."),
    INVALID_JOIN_CODE_ERROR(HttpStatus.BAD_REQUEST, "비밀번호의 조건이 부합하지 않습니다."),
    NOT_CONTAINS_GROUP_USER_ERROR(HttpStatus.BAD_REQUEST, "그룹원이 아닙니다."),
    NOT_FOUND_GROUP_ID_ERROR(HttpStatus.NOT_FOUND, "group Id를 찾을 수 없습니다."),
    NOT_FOUND_GROUP_TAG_ERROR(HttpStatus.NOT_FOUND, "그룹 태그 정보를 찾을 수 없습니다."),
    OVER_RANGE_GROUP_NAME_ERROR(HttpStatus.BAD_REQUEST, "그룹명은 16글자까지만 입력할 수 있습니다."),
    OVER_COUNT_GROUP_TAG_ERROR(HttpStatus.BAD_REQUEST, "그룹 태그는 최대 3개까지 입력 가능합니다."),
    DUPLICATE_GROUP_TAG_NAME_ERROR(HttpStatus.BAD_REQUEST, "중복된 태그명이 있습니다."),

    /** Group User Error Code */
    DUPLICATE_GROUP_JOIN_ERROR(HttpStatus.BAD_REQUEST, "한개의 그룹만 참여 가능합니다."),
    EXCEED_GROUP_USER_COUNT_ERROR(HttpStatus.BAD_REQUEST, "그룹 정원을 초과하였습니다."),
    NOT_FOUND_GROUP_USER_ERROR(HttpStatus.NOT_FOUND, "그룹 유저 정보를 찾을 수 없습니다."),

    /** Image Client Error Code */
    IMAGE_CLIENT_UPLOAD_ERROR(HttpStatus.BAD_REQUEST, "이미지 업로드 중 오류가 발생했습니다."),

    /** Pose Notification Error Code */
    NOT_FOUND_POSE_NOTIFICATION_ERROR(HttpStatus.NOT_FOUND, "자세 알림 정보를 찾을 수 없습니다."),

    /** Pose Layout Error Code */
    NOT_FOUND_POSE_LAYOUT_ERROR(HttpStatus.NOT_FOUND, "포즈 레이아웃을 찾을 수 없습니다."),

    /** discord */
    NOT_FOUND_TARGET_TOKEN(HttpStatus.NOT_FOUND, "디스코드 발송 토큰이 없습니다."),
    ;
}
