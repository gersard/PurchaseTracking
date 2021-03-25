package cl.gersard.shoppingtracking.data.purchase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "Purchase")
data class PurchaseEntity(
    @PrimaryKey(autoGenerate = true) val purchaseId: Long,
    @ColumnInfo(name = "total") val total: Int,
    @ColumnInfo(name = "quantity") val quantity: Int,
    @ColumnInfo(name = "date") val date: LocalDateTime,
    @ColumnInfo(name = "discount") val discount: Boolean,
    @ColumnInfo(name = "note") val note: String,
    @ColumnInfo(name = "market_owner_id") val marketOwnerId: Long,
)
