package com.goofy.springwebfluxandcoroutines.service

import com.goofy.springwebfluxandcoroutines.common.model.OffsetResponse
import com.goofy.springwebfluxandcoroutines.domain.Status
import com.goofy.springwebfluxandcoroutines.domain.Todo
import com.goofy.springwebfluxandcoroutines.exception.ErrorCode
import com.goofy.springwebfluxandcoroutines.exception.NotFoundException
import com.goofy.springwebfluxandcoroutines.model.TodoRequest
import com.goofy.springwebfluxandcoroutines.model.TodoResponse
import com.goofy.springwebfluxandcoroutines.repository.SuspendableTodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.withContext
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class TodoService(
    private val suspendableTodoRepository: SuspendableTodoRepository,
) {
    suspend fun create(request: TodoRequest): TodoResponse {
        val todo = Todo(
            status = request.status,
            title = request.title,
            content = request.content
        )

        val createdTodo = suspendableTodoRepository.save(todo)

        return TodoResponse.from(createdTodo)
    }

    suspend fun update(id: Long, request: TodoRequest): TodoResponse {
        val todo = suspendableTodoRepository.findById(id)
            ?: throw NotFoundException(ErrorCode.NOT_FOUND_TODO_ERROR)

        val updatedTodo = suspendableTodoRepository.save(
            todo.apply {
                this.status = request.status
                this.title = request.title
                this.content = request.content
            }
        )

        return TodoResponse.from(updatedTodo)
    }

    suspend fun get(id: Long): TodoResponse {
        val todo = suspendableTodoRepository.findById(id)
            ?: throw NotFoundException(ErrorCode.NOT_FOUND_TODO_ERROR)

        return TodoResponse.from(todo)
    }

    suspend fun search(status: Status, offset: Long, size: Int): OffsetResponse<TodoResponse> {
        val data = withContext(Dispatchers.IO) {
            suspendableTodoRepository.findAllByStatus(status, offset, size)
        }.map { todo -> TodoResponse.from(todo) }.toList()

        return OffsetResponse(
            data = data,
            lastOffset = data.lastOrNull()?.id,
            size = data.size,
            sort = Sort.by(Sort.Order.desc("createdAt")),
            hasNext = data.size == size
        )
    }
}
