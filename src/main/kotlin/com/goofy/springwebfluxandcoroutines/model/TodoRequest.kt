package com.goofy.springwebfluxandcoroutines.model

import com.goofy.springwebfluxandcoroutines.domain.Status

data class TodoRequest(
    val status: Status,
    val title: String,
    val content: String,
)
