package cl.gersard.shoppingtracking.data.product.local.model

import androidx.room.Entity
import androidx.room.Index

@Entity(primaryKeys = ["productId", "purchaseId"], indices = [Index(value = ["purchaseId"])])
data class ProductPurchaseCrossRef(
    val productId: Long,
    val purchaseId: Long
)