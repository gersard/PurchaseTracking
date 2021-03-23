package cl.gersard.shoppingtracking.data.product.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Product")
data class ProductEntity(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true) val productId: Long,
    @ColumnInfo(name = "barcode") val barcode: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "note") val note: String,
)
