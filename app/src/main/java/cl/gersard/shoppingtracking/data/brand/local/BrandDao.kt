package cl.gersard.shoppingtracking.data.brand.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import cl.gersard.shoppingtracking.data.brand.BrandEntity

@Dao
interface BrandDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertBrand(brandEntity: BrandEntity): Long

    @Query("SELECT * FROM Brand")
    suspend fun getAllBrands(): List<BrandEntity>

    @Query("SELECT * FROM Brand WHERE brandId = :idBrand")
    suspend fun getBrand(idBrand: Long): BrandEntity?

    @Query("SELECT * FROM Brand WHERE name = :brandName")
    suspend fun getBrand(brandName: String): BrandEntity?

}