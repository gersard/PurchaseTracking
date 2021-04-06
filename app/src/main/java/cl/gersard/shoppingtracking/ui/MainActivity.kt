package cl.gersard.shoppingtracking.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import cl.gersard.shoppingtracking.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun changeFragment(fragment: Fragment, popStack: Boolean) {
        if (popStack) supportFragmentManager.popBackStack()

        val transaction = supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.host_fragment, fragment, fragment::class.java.simpleName)

        transaction.commit()
    }
}