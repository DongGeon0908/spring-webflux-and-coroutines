package com.goofy.springwebfluxandcoroutines.model

import com.goofy.springwebfluxandcoroutines.domain.Status
import com.goofy.springwebfluxandcoroutines.domain.Todo
import java.time.LocalDateTime

data class TodoResponse(
    val id: Long,
    val status: Status,
    val title: String,
    val content: String,
    val createdAt: LocalDateTime,
    val modifiedAt: LocalDateTime,
) {
    companion object {
        fun from(todo: Todo): TodoResponse {
            return TodoResponse(
                id = todo.id,
                status = todo.status,
                title = todo.title,
                content = todo.content,
                createdAt = todo.createdAt,
                modifiedAt = todo.modifiedAt
            )
        }
    }
}
