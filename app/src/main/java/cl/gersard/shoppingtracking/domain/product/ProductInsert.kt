package cl.gersard.shoppingtracking.domain.product

import cl.gersard.shoppingtracking.domain.brand.Brand

// Exclusive to insert product
data class ProductInsert(
    val id: Long,
    val barcode: String,
    val name: String,
    val description: String,
    val brandId: Long,
    val note: String,
)
