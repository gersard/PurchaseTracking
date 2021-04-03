package cl.gersard.shoppingtracking.core

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DateUtils {

    const val FORMAT_PURCHASE_FORM = "dd/MM/yy"

    fun parseDate(date: String, pattern: String): LocalDate {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern))
    }


}