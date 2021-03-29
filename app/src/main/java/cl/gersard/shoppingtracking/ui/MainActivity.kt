package cl.gersard.shoppingtracking.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import cl.gersard.shoppingtracking.R
import cl.gersard.shoppingtracking.domain.brand.Brand
import cl.gersard.shoppingtracking.domain.brand.BrandUseCase
import cl.gersard.shoppingtracking.domain.market.Market
import cl.gersard.shoppingtracking.domain.market.MarketUseCase
import cl.gersard.shoppingtracking.domain.product.Product
import cl.gersard.shoppingtracking.domain.product.ProductUseCase
import cl.gersard.shoppingtracking.domain.purchase.Purchase
import cl.gersard.shoppingtracking.domain.purchase.PurchaseUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var brandUseCase: BrandUseCase

    @Inject
    lateinit var marketUseCase: MarketUseCase

    @Inject
    lateinit var productUseCase: ProductUseCase

    @Inject
    lateinit var purchaseUseCase: PurchaseUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            // TEST BRANDS
//            brandUseCase.insertBrand("Colún")
//            brandUseCase.insertBrand("Ideal")
//            brandUseCase.insertBrand("Surlat")
//            brandUseCase.insertBrand("Cachantún")
//            brandUseCase.insertBrand("PC Factory")
//
//            // TEST MARKETS
//            marketUseCase.insertMarket("Tottus")
//            marketUseCase.insertMarket("Jumbo")
//            marketUseCase.insertMarket("Falabella")
//            marketUseCase.insertMarket("casa royal")
//            marketUseCase.insertMarket("PC Factory")
//
//            // TEST PRODUCT
//            productUseCase.insertProduct(
//                Product(
//                    1, "0001928376123", "Leche", "leche de 1 litro descremada", Brand(1, "Colún"), "el envase es de celeste",
//                    emptyList()
//                )
//            )
//
//            // TEST PURCHASE
//            purchaseUseCase.insertPurchase(
//                Purchase(
//                    1,
//                    720,
//                    1,
//                    LocalDateTime.now(),
//                    Market(1, ""),
//                    false,
//                    ""
//                )
//            )
//
//            productUseCase.insertProductPurchase(1, 1)

        }
    }

    fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.host_fragment, fragment)
            .addToBackStack(null)
            .commit()
    }
}