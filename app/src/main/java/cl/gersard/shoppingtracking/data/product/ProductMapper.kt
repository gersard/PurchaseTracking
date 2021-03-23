package cl.gersard.shoppingtracking.data.product

import cl.gersard.shoppingtracking.data.brand.BrandMapper
import cl.gersard.shoppingtracking.data.product.local.model.ProductEntity
import cl.gersard.shoppingtracking.data.product.local.model.ProductWithPurchases
import cl.gersard.shoppingtracking.data.purchase.PurchaseMapper
import cl.gersard.shoppingtracking.domain.Product
import javax.inject.Inject

class ProductMapper @Inject constructor
    (
    private val brandMapper: BrandMapper
) {

    fun mapToProductDomain(productWithPurchases: List<ProductWithPurchases>): List<Product> = productWithPurchases.map {
        Product(
            it.product.productId,
            it.product.barcode,
            it.product.name,
            it.product.description,
            brandMapper.mapToBrandDomain(it.brand),
            it.product.note
        )
    }

}