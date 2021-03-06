package cl.gersard.shoppingtracking.data.market

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Market")
data class MarketEntity(
    @PrimaryKey(autoGenerate = true)
    val marketId: Long,
    @ColumnInfo(name = "name")
    val name: String
)
