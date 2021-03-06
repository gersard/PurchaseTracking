package cl.gersard.shoppingtracking.data.brand.local

import cl.gersard.shoppingtracking.data.brand.BrandEntity

interface BrandDataSource {

    suspend fun insertBrand(brandEntity: BrandEntity): Long

    suspend fun getAllBrands(): List<BrandEntity>

    suspend fun getBrand(brandName: String): BrandEntity?
}