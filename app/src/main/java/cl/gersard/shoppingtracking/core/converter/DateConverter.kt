package cl.gersard.shoppingtracking.core.converter

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

class DateConverter {

    @TypeConverter
    fun fromTimestamp(timestamp: Long): LocalDateTime {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault())
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDateTime): Long {
        return date.toInstant(ZoneOffset.UTC).toEpochMilli()
    }
}
