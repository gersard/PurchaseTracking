package cl.gersard.shoppingtracking.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "brand")
data class BrandEntity(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    val brandId: Long,
    @ColumnInfo(name = "name")
    val name: String
)
