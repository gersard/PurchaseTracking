package cl.gersard.shoppingtracking.core

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import cl.gersard.shoppingtracking.core.converter.DateConverter
import cl.gersard.shoppingtracking.data.brand.BrandEntity
import cl.gersard.shoppingtracking.data.market.MarketEntity
import cl.gersard.shoppingtracking.data.product.local.ProductDao
import cl.gersard.shoppingtracking.data.product.local.model.ProductEntity
import cl.gersard.shoppingtracking.data.product.local.model.ProductPurchaseCrossRef
import cl.gersard.shoppingtracking.data.purchase.PurchaseEntity
import cl.gersard.shoppingtracking.data.purchase.local.PurchaseDao

@Database(
    entities = [ProductEntity::class, ProductPurchaseCrossRef::class, PurchaseEntity::class, BrandEntity::class, MarketEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun purchaseDao(): PurchaseDao

    companion object {
        const val DATABASE_NAME = "purchase_tracking_app"
    }

}