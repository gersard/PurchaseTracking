package cl.gersard.shoppingtracking.data.market

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "market")
data class MarketEntity(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    val marketId: Long,
    @ColumnInfo(name = "name")
    val name: String
)
