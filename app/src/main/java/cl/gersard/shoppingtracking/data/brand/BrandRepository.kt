package cl.gersard.shoppingtracking.data.brand

import cl.gersard.shoppingtracking.domain.brand.Brand

interface BrandRepository {

    suspend fun insertBrand(brand: Brand): Boolean

}