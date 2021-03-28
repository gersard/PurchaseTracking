package cl.gersard.shoppingtracking.data.brand.local

import cl.gersard.shoppingtracking.data.brand.BrandEntity

interface BrandDataSource {

    suspend fun insertBrand(brandEntity: BrandEntity): Long

}