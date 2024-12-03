package com.goofy.springwebfluxandcoroutines.repository

import com.goofy.springwebfluxandcoroutines.domain.Status
import com.goofy.springwebfluxandcoroutines.domain.Todo
import kotlinx.coroutines.flow.Flow
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SuspendableTodoRepository : CoroutineCrudRepository<Todo, Long> {
    @Query(
        """
        SELECT * FROM todo 
        WHERE status = :status 
        AND id > :lastId
        ORDER BY id DESC
        LIMIT :limit
    """
    )
    suspend fun findAllByStatus(status: Status, lastId: Long, limit: Int): Flow<Todo>
}
