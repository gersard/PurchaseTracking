package cl.gersard.shoppingtracking.data.purchase.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "Purchase")
data class PurchaseEntity(
    @PrimaryKey(autoGenerate = true) val purchaseId: Long,
    @ColumnInfo(name = "price") val price: Int,
    @ColumnInfo(name = "quantity") val quantity: Int,
    @ColumnInfo(name = "date") val date: LocalDate,
    @ColumnInfo(name = "discount") val discount: Boolean,
    @ColumnInfo(name = "note") val note: String,
    @ColumnInfo(name = "market_owner_id") val marketOwnerId: Long,
    @ColumnInfo(name = "product_owner_id") val productOwnerId: Long,
)
