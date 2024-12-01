package com.goofy.springwebfluxandcoroutines.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table(name = "todo")
class Todo(
    @Id
    @Column("id")
    val id: Long = 0L,

    @Column("status")
    var status: Status,

    @Column("title")
    var title: String,

    @Column("content")
    var content: String,
) : BaseEntity()
