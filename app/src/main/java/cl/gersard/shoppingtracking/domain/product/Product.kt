package cl.gersard.shoppingtracking.domain.product

import cl.gersard.shoppingtracking.domain.brand.Brand
import cl.gersard.shoppingtracking.domain.purchase.Purchase

data class Product(
    val id: Long,
    val barcode: String,
    val name: String,
    val description: String,
    val brand: Brand,
    val note: String,
    val purchases: List<Purchase>?
)
