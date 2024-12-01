package com.goofy.springwebfluxandcoroutines.exception

open class GoofyException(
    val errorCode: ErrorCode,
    override val message: String? = errorCode.description,
    val extra: Map<String, Any>? = null,
) : RuntimeException(message ?: errorCode.description)

class NotFoundException(errorCode: ErrorCode) : GoofyException(errorCode)

class InvalidTokenException(errorCode: ErrorCode) : GoofyException(errorCode)

class InvalidRequestException(errorCode: ErrorCode, message: String? = null) : GoofyException(errorCode, message)

class FailToCreateException(errorCode: ErrorCode) : GoofyException(errorCode)

class AlreadyException(errorCode: ErrorCode) : GoofyException(errorCode)

class NoAuthorityException(errorCode: ErrorCode) : GoofyException(errorCode)

class FailToExecuteException(errorCode: ErrorCode) : GoofyException(errorCode)

class RedisPubSubException(errorCode: ErrorCode) : GoofyException(errorCode)

/** Image Exception */
class ImageUploadException : GoofyException(ErrorCode.IMAGE_CLIENT_UPLOAD_ERROR)
