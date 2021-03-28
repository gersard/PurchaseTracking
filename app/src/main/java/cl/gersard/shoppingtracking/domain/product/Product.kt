package cl.gersard.shoppingtracking.domain.product

import cl.gersard.shoppingtracking.domain.Brand
import cl.gersard.shoppingtracking.domain.Purchase

data class Product(
    val id: Long,
    val barcode: String,
    val name: String,
    val description: String,
    val brand: Brand,
    val note: String,
    val purchases: List<Purchase>
)
