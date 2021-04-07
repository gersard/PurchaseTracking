package cl.gersard.shoppingtracking.data.market.local

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
class MarketDataSourceTest {

    private lateinit var db: AppDatabase
    private lateinit var marketDao: MarketDao
    private lateinit var dataSource: MarketDataSource

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        marketDao = db.marketDao()
        dataSource = MarketDataSourceImpl(marketDao)
    }

    @Test
    fun insertMarket() {
        runBlocking {
            val marketEntity = MarketUtil.fakeMarketEntity
            val idInserted = dataSource.insertMarket(marketEntity)
            assertEquals(marketEntity.marketId, idInserted)
        }
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

}