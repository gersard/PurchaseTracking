package cl.gersard.shoppingtracking

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import cl.gersard.shoppingtracking.core.converter.DateConverter
import cl.gersard.shoppingtracking.data.BrandEntity
import cl.gersard.shoppingtracking.data.MarketEntity
import cl.gersard.shoppingtracking.data.product.ProductEntity
import cl.gersard.shoppingtracking.data.purchase.PurchaseEntity
import cl.gersard.shoppingtracking.domain.Product

@Database(
    entities = [ProductEntity::class, PurchaseEntity::class, BrandEntity::class, MarketEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "tracking_app"
//        private var instance: AppDatabase? = null

//        private fun create(context: Context): AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()

//        fun getInstance(context: Context) = (instance ?: create(context)).also { instance = it }
    }

}