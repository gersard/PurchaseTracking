package cl.gersard.shoppingtracking.core.extension

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

fun LocalDateTime.format(format: String): String {
    val formatter = DateTimeFormatter.ofPattern(format)
    return formatter.format(this)
}

fun LocalDate.format(format: String): String {
    val formatter = DateTimeFormatter.ofPattern(format)
    return formatter.format(this)
}

fun OffsetDateTime.format(format: String): String {
    val formatter = DateTimeFormatter.ofPattern(format)
    return formatter.format(this)
}