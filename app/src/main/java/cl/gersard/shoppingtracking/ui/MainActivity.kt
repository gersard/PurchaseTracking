package cl.gersard.shoppingtracking.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import cl.gersard.shoppingtracking.R
import cl.gersard.shoppingtracking.domain.brand.BrandUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var useCase: BrandUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            useCase.insertBrand("Tottus")
            useCase.insertBrand("Jumbo")
            useCase.insertBrand("Falabella")
            useCase.insertBrand("casa royal")
            useCase.insertBrand("PC Factory")
        }
    }
}