package cl.gersard.shoppingtracking.data.purchase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "purchase")
data class PurchaseEntity(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "total") val total: Int,
    @ColumnInfo(name = "quantity") val quantity: Int,
    @ColumnInfo(name = "date") val date: Date,
    @ColumnInfo(name = "discount") val discount: Boolean,
    @ColumnInfo(name = "note") val note: String,
)
