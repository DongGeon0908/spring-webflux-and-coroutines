package com.goofy.springwebfluxandcoroutines.exception.advice

import com.goofy.springwebfluxandcoroutines.exception.GoofyException
import com.goofy.springwebfluxandcoroutines.common.model.ErrorResponse
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class GoofyExceptionHandler {
    private val logger = KotlinLogging.logger { }

    @ExceptionHandler(GoofyException::class)
    protected fun handleGoofyException(e: GoofyException): ResponseEntity<ErrorResponse> {
        logger.warn { "GoofyException ${e.message}" }
        val response = ErrorResponse(
            errorCode = e.errorCode.name,
            reason = e.message ?: e.errorCode.description,
            extra = e.extra
        )
        return ResponseEntity(response, e.errorCode.status)
    }
}
