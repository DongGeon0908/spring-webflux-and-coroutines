package com.goofy.springwebfluxandcoroutines.repository

import com.goofy.springwebfluxandcoroutines.domain.Status
import com.goofy.springwebfluxandcoroutines.domain.Todo
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface SuspendableTodoRepository : CoroutineCrudRepository<Todo, Long>

@Repository
interface ReactiveTodoRepository : R2dbcRepository<Todo, Long> {
    fun findAllByStatus(status: Status, pageable: Pageable): Flux<Page<Todo>>
}
