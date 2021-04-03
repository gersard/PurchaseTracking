package cl.gersard.shoppingtracking.data.product

import cl.gersard.shoppingtracking.data.brand.BrandMapper
import cl.gersard.shoppingtracking.data.product.local.model.ProductEntity
import cl.gersard.shoppingtracking.data.product.local.model.ProductWithBrand
import cl.gersard.shoppingtracking.data.product.local.model.ProductWithPurchases
import cl.gersard.shoppingtracking.data.purchase.PurchaseMapper
import cl.gersard.shoppingtracking.domain.product.Product
import cl.gersard.shoppingtracking.domain.product.ProductInsert
import javax.inject.Inject

class ProductMapper @Inject constructor
    (
    private val purchaseMapper: PurchaseMapper,
    private val brandMapper: BrandMapper
) {

    fun mapToProductDomain(productWithPurchases: List<ProductWithPurchases>): List<Product> = productWithPurchases.map {
        Product(
            it.product.productId,
            it.product.barcode,
            it.product.name,
            it.product.description,
            brandMapper.mapToBrandDomain(it.brand),
            it.product.note,
            purchaseMapper.mapToPurchaseDomain(it.purchases)
        )
    }

    fun mapToProductDomain(productEntity: ProductWithBrand) = Product(
        productEntity.product.productId,
        productEntity.product.barcode,
        productEntity.product.name,
        productEntity.product.description,
        brandMapper.mapToBrandDomain(productEntity.brand),
        productEntity.product.note
    )

    fun mapToProductEntity(product: Product) = ProductEntity(
        product.id,
        product.barcode,
        product.name,
        product.description,
        product.note,
        product.brand.id
    )

    fun mapToProductEntity(product: ProductInsert) = ProductEntity(
        product.id,
        product.barcode,
        product.name,
        product.description,
        product.note,
        product.brandId
    )

}