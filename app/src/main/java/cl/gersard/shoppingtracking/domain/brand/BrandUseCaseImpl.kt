package cl.gersard.shoppingtracking.domain.brand

import cl.gersard.shoppingtracking.data.brand.BrandRepository
import java.util.*
import javax.inject.Inject

class BrandUseCaseImpl @Inject constructor(private val repository: BrandRepository) : BrandUseCase {

    override suspend fun insertBrand(name: String): Long {
        return if (name.isNotEmpty()) {
            repository.insertBrand(Brand(0, name.capitalize(Locale.ROOT)))
        } else {
            throw Exception("Name must not be empty")
        }
    }

    override suspend fun getBrands(): List<Brand> {
        return repository.getAllBrands()
    }

    override suspend fun getBrand(brandName: String): Brand? {
        return repository.getBrand(brandName)
    }
}