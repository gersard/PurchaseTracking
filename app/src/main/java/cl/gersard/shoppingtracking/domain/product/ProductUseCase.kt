package cl.gersard.shoppingtracking.domain.product

interface ProductUseCase {

    suspend fun getProductsWithPurchases(): List<Product>

    suspend fun insertProduct(id: Long,name: String, description: String, barcode: String, brandId: Long, note: String)

    suspend fun insertProductPurchase(idProduct: Long, idPurchase: Long)

    suspend fun searchProduct(barcode: String): ProductState<Product>

}