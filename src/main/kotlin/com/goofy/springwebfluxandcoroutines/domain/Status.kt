package com.goofy.springwebfluxandcoroutines.domain

import kotlin.random.Random

enum class Status {
    PREPARE,
    PROCEED,
    COMPLETE,
    ;

    fun getRandomStatus(): Status {
        val statuses = entries.toTypedArray()
        return statuses[Random.nextInt(statuses.size)]
    }
}
