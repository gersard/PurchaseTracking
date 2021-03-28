package cl.gersard.shoppingtracking.data.brand

import cl.gersard.shoppingtracking.domain.brand.Brand

class BrandMapper {

    fun mapToBrandDomain(brandEntity: BrandEntity) = Brand(
        brandEntity.brandId, brandEntity.name,
    )

    fun mapToBrandEntity(brand: Brand) = BrandEntity(
        brand.id, brand.name
    )

}