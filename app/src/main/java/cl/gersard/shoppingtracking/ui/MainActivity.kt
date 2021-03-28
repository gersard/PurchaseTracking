package cl.gersard.shoppingtracking.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import cl.gersard.shoppingtracking.R
import cl.gersard.shoppingtracking.domain.brand.BrandUseCase
import cl.gersard.shoppingtracking.domain.market.MarketUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var brandUseCase: BrandUseCase

    @Inject
    lateinit var marketUseCase: MarketUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            // TEST BRANDS
            brandUseCase.insertBrand("Colún")
            brandUseCase.insertBrand("Ideal")
            brandUseCase.insertBrand("Surlat")
            brandUseCase.insertBrand("Cachantún")
            brandUseCase.insertBrand("PC Factory")

            // TEST MARKETS
            marketUseCase.insertMarket("Tottus")
            marketUseCase.insertMarket("Jumbo")
            marketUseCase.insertMarket("Falabella")
            marketUseCase.insertMarket("casa royal")
            marketUseCase.insertMarket("PC Factory")
        }
    }
}