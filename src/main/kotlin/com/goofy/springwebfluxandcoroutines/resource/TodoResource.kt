package com.goofy.springwebfluxandcoroutines.resource

import com.goofy.springwebfluxandcoroutines.common.extension.wrapCreated
import com.goofy.springwebfluxandcoroutines.common.extension.wrapOk
import com.goofy.springwebfluxandcoroutines.common.extension.wrapPage
import com.goofy.springwebfluxandcoroutines.common.model.PageResponse
import com.goofy.springwebfluxandcoroutines.common.model.Response
import com.goofy.springwebfluxandcoroutines.domain.Status
import com.goofy.springwebfluxandcoroutines.model.TodoRequest
import com.goofy.springwebfluxandcoroutines.model.TodoResponse
import com.goofy.springwebfluxandcoroutines.service.TodoService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
class TodoResource(
    private val todoService: TodoService,
) {
    @Operation(summary = "todo 생성")
    @PostMapping("/api/v1/todos")
    suspend fun createTodo(
        @RequestBody request: TodoRequest,
    ): ResponseEntity<Response<TodoResponse>> {
        return todoService.create(request).wrapCreated()
    }

    @Operation(summary = "todo 수정")
    @PutMapping("/api/v1/todos/{id}")
    suspend fun updateTodo(
        @PathVariable id: Long,
        @RequestBody request: TodoRequest,
    ): ResponseEntity<Response<TodoResponse>> {
        return todoService.update(id, request).wrapOk()
    }

    @Operation(summary = "todo 상세 조회")
    @GetMapping("/api/v1/todos/{id}")
    suspend fun getTodo(
        @PathVariable id: Long
    ): ResponseEntity<Response<TodoResponse>> {
        return todoService.get(id).wrapOk()
    }

    @Operation(summary = "todo 검색")
    @GetMapping("/api/v1/todos")
    suspend fun search(
        @RequestParam status: Status,
        @PageableDefault(
            page = 0,
            size = 200,
            sort = ["createdAt"],
            direction = Sort.Direction.DESC
        ) pageable: Pageable,
    ): ResponseEntity<PageResponse<TodoResponse>> {
        return todoService.search(status, pageable).wrapPage()
    }
}
