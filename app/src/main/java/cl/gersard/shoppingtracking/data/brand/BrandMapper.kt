package cl.gersard.shoppingtracking.data.brand

import cl.gersard.shoppingtracking.domain.Brand

class BrandMapper {

    fun mapToBrandDomain(brandEntity: BrandEntity) = Brand(
        brandEntity.brandId, brandEntity.name,
    )

}