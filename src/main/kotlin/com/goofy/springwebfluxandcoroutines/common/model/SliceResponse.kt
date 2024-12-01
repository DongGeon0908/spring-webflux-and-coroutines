package com.goofy.springwebfluxandcoroutines.common.model

import org.springframework.data.domain.Slice
import org.springframework.data.domain.Sort

data class SliceResponse<T>(
    val data: List<T>,
    val page: Int?,
    val size: Int?,
    val sort: Sort,
    val hasNext: Boolean,
) {
    constructor(slice: Slice<T>) : this(
        data = slice.content,
        page = slice.pageable.pageNumber,
        size = slice.size,
        sort = slice.sort,
        hasNext = slice.hasNext()
    )
}
