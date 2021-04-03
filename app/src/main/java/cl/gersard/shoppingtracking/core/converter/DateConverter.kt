package cl.gersard.shoppingtracking.core.converter

import androidx.room.TypeConverter
import java.time.*

class DateConverter {

    @TypeConverter
    fun fromTimestamp(timestamp: Long): LocalDate {
        return Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault()).toLocalDate()
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDate): Long {
        return date.atStartOfDay(ZoneId.systemDefault()).toEpochSecond()
    }
}
