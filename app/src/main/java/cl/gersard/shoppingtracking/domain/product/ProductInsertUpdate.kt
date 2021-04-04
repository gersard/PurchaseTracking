package cl.gersard.shoppingtracking.domain.product


// Exclusive to insert or update product
data class ProductInsertUpdate(
    val id: Long,
    val barcode: String,
    val name: String,
    val description: String,
    val brandId: Long,
    val note: String,
)
