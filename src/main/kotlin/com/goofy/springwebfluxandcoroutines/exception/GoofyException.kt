package com.goofy.springwebfluxandcoroutines.exception

open class GoofyException(
    val errorCode: ErrorCode,
    override val message: String? = errorCode.description,
    val extra: Map<String, Any>? = null,
) : RuntimeException(message ?: errorCode.description)

class NotFoundException(errorCode: ErrorCode) : GoofyException(errorCode)
