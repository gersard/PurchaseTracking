package cl.gersard.shoppingtracking.domain.brand

interface BrandUseCase {

    suspend fun insertBrand(name: String): Long

    suspend fun getBrands(): List<Brand>

    suspend fun getBrand(brandName: String): Brand?

}