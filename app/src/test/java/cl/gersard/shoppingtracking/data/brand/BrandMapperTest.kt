package cl.gersard.shoppingtracking.data.brand

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class BrandMapperTest {
    lateinit var mapper: BrandMapper

    @Before
    fun initMapperInstance() {
        mapper = BrandMapper()
    }

    @Test
    fun brandEntityToBrandDomain() {
        val brandEntity = BrandEntity(1, "brand")
        val brandDomain = mapper.mapToBrandDomain(brandEntity)

        assertEquals(brandDomain.id, brandEntity.brandId)
        assertEquals(brandDomain.name, brandEntity.name)
    }
}
