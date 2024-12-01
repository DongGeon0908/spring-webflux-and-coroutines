package com.goofy.springwebfluxandcoroutines.service

import com.goofy.springwebfluxandcoroutines.domain.Status
import com.goofy.springwebfluxandcoroutines.domain.Todo
import com.goofy.springwebfluxandcoroutines.exception.ErrorCode
import com.goofy.springwebfluxandcoroutines.exception.NotFoundException
import com.goofy.springwebfluxandcoroutines.model.TodoRequest
import com.goofy.springwebfluxandcoroutines.model.TodoResponse
import com.goofy.springwebfluxandcoroutines.repository.ReactiveTodoRepository
import com.goofy.springwebfluxandcoroutines.repository.SuspendableTodoRepository
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TodoService(
    private val suspendableTodoRepository: SuspendableTodoRepository,
    private val reactiveTodoRepository: ReactiveTodoRepository,
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

    suspend fun search(status: Status, pageable: Pageable): Page<TodoResponse> {
        return reactiveTodoRepository.findAllByStatus(status, pageable).awaitSingle()
            .map { todo -> TodoResponse.from(todo) }
    }
}
