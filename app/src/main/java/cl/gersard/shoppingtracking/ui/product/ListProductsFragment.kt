package cl.gersard.shoppingtracking.ui.product

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import cl.gersard.shoppingtracking.R
import cl.gersard.shoppingtracking.databinding.ListProductsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListProductsFragment : Fragment() {

    private var _viewBinding: ListProductsFragmentBinding? = null
    private val viewBinding: ListProductsFragmentBinding get() = _viewBinding!!
    private val viewModel: ListProductsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = ListProductsFragmentBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    companion object {
        fun newInstance() = ListProductsFragment()
    }

}