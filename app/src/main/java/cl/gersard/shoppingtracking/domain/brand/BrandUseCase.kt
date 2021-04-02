package cl.gersard.shoppingtracking.domain.brand

interface BrandUseCase {

    suspend fun insertBrand(name: String)

    suspend fun getBrands(): List<Brand>

}