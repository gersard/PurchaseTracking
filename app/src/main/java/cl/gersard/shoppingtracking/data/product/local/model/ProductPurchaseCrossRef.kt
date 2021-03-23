package cl.gersard.shoppingtracking.data.product.local.model

import androidx.room.Entity

@Entity(primaryKeys = ["productId, purchaseId"])
data class ProductPurchaseCrossRef(
    val productId: Long,
    val purchaseId: Long
)