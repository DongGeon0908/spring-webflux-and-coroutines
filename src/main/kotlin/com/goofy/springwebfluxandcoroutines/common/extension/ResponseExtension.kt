package com.goofy.springwebfluxandcoroutines.common.extension

import com.goofy.springwebfluxandcoroutines.common.model.Response
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.net.URI

fun <T> T.wrap() = ResponseEntity.ok(this)

/** Wrap Response Ok */
fun <T> T.wrapOk() = ResponseEntity.ok(Response(this))

/** Wrap Response Created */
fun <T> T.wrapCreated() = ResponseEntity.status(HttpStatus.CREATED)
    .body(Response(this))

/** Wrap Response Void */
fun Unit.wrapVoid() = ResponseEntity.noContent().build<Unit>()

/** Wrap Response Redirect */
fun String.wrapRedirected(): ResponseEntity<Unit> {
    val headers = HttpHeaders()
    headers.location = URI.create(this)
    return ResponseEntity<Unit>(headers, HttpStatus.PERMANENT_REDIRECT)
}
