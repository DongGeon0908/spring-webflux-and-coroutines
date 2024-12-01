package com.goofy.springwebfluxandcoroutines.common.extension

import java.time.*
import java.time.format.DateTimeFormatter

object Zone {
    val KST: ZoneId = ZoneId.of("Asia/Seoul")
    val UTC: ZoneId = ZoneId.of("UTC")
}

fun LocalDateTime.toInstant(): Instant {
    return this.toInstant(ZoneOffset.of("+09:00"))
}

fun LocalDateTime.equalsFromYearToSec(otherTime: LocalDateTime): Boolean {
    return this.year == otherTime.year &&
            this.month == otherTime.month &&
            this.dayOfMonth == otherTime.dayOfMonth &&
            this.hour == otherTime.hour &&
            this.minute == otherTime.minute &&
            this.second == otherTime.second
}

fun LocalDateTime.format(format: String): String {
    val formatter = DateTimeFormatter.ofPattern(format)
    return this.format(formatter)
}

fun LocalDate.yearMonth(): String {
    val year = this.year
    val month = if (this.monthValue >= 10) {
        this.monthValue
    } else {
        "0${this.monthValue}"
    }

    return "$year$month"
}
