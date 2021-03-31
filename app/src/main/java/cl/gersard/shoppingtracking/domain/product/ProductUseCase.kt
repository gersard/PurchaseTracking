package cl.gersard.shoppingtracking.domain.product

interface ProductUseCase {

    suspend fun getProductsWithPurchases(): List<Product>

    suspend fun insertProduct(product: Product)

    suspend fun insertProductPurchase(idProduct: Long, idPurchase: Long)

    suspend fun searchProduct(barcode: String): ProductState<Product>

}