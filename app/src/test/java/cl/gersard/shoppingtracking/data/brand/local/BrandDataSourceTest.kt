package cl.gersard.shoppingtracking.data.brand.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import cl.gersard.shoppingtracking.core.AppDatabase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import kotlin.jvm.Throws

@RunWith(AndroidJUnit4::class)
class BrandDataSourceTest {

    private lateinit var db: AppDatabase
    private lateinit var brandDao: BrandDao
    private lateinit var dataSource: BrandDataSource

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        brandDao = db.brandDao()
        dataSource = BrandDataSourceImpl(brandDao)
    }

    @Test
    fun insertBrand() {
        runBlocking {
            val brandEntity = BrandUtil.fakeBrandEntity
            val idInserted = dataSource.insertBrand(brandEntity)
            assertEquals(brandEntity.brandId, idInserted)
        }
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

}