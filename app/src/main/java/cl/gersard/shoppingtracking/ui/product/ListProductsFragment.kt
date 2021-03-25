package cl.gersard.shoppingtracking.ui.product

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
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
        setupRecyclerView()
        observeLoading()
        observeProducts()
        viewModel.fetchProducts()
    }

    private fun observeProducts() {
        TODO("Not yet implemented")
    }

    private fun observeLoading() {
        viewModel.loadingState.observe(viewLifecycleOwner, Observer {
            if (it) {
                viewBinding.pbLoadingProducts.visibility = View.VISIBLE
            } else {
                viewBinding.pbLoadingProducts.visibility = View.GONE
            }
        })
    }

    private fun setupRecyclerView() {
        TODO("Not yet implemented")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    companion object {
        fun newInstance() = ListProductsFragment()
    }

}