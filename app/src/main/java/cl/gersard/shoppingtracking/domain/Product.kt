package cl.gersard.shoppingtracking.domain

data class Product(
    val id: Long,
    val barcode: String,
    val name: String,
    val description: String,
    val brand: Brand,
    val note: String
)
