package com.goofy.springwebfluxandcoroutines.common.model

import org.springframework.data.domain.Sort

data class OffsetResponse<T>(
    val data: List<T>,
    val lastOffset: Long?,
    val size: Int,
    val sort: Sort,
    val hasNext: Boolean,
)
