package cl.gersard.shoppingtracking.ui.product.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import cl.gersard.shoppingtracking.R
import cl.gersard.shoppingtracking.core.extension.gone
import cl.gersard.shoppingtracking.core.extension.visible
import cl.gersard.shoppingtracking.databinding.ListProductsFragmentBinding
import cl.gersard.shoppingtracking.domain.product.Product
import cl.gersard.shoppingtracking.domain.product.ProductState
import cl.gersard.shoppingtracking.ui.MainActivity
import cl.gersard.shoppingtracking.ui.product.list.adapter.ProductAdapter
import cl.gersard.shoppingtracking.ui.scan.ScanFragment
import cl.gersard.shoppingtracking.ui.util.MarginItemDecorator
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
        viewBinding.btnEmptyProducts.setOnClickListener { viewModel.fetchProducts() }
        viewBinding.fabAddPurchase.setOnClickListener { addPurchase() }
    }

    private fun addPurchase() {
        (requireActivity() as MainActivity).changeFragment(ScanFragment.newInstance(),false)
    }

    private fun observeLoading() {
        viewModel.loadingState.observe(viewLifecycleOwner, {
            if (it) {
                viewBinding.pbLoadingProducts.visible()
            } else {
                viewBinding.pbLoadingProducts.gone()
            }
        })
    }

    private fun observeProducts() {
        viewModel.productState.observe(viewLifecycleOwner, { productState ->
            when (productState) {
                ProductState.Empty -> showEmptyButton()
                is ProductState.Error -> Toast.makeText(requireContext(), productState.error, Toast.LENGTH_LONG).show()
                is ProductState.Success -> loadProducts(productState.data)
            }
        })
    }

    private fun loadProducts(products: List<Product>) {
        viewBinding.btnEmptyProducts.gone()
        viewBinding.rvProducts.visible()
        (viewBinding.rvProducts.adapter as ProductAdapter).addProducts(products)
    }

    private fun showEmptyButton() {
        viewBinding.btnEmptyProducts.visible()
    }


    private fun setupRecyclerView() {
        viewBinding.rvProducts.setHasFixedSize(true)
        viewBinding.rvProducts.addItemDecoration(
            MarginItemDecorator(
                resources.getDimension(R.dimen.margin_vertical_product_list_item).toInt(),
                resources.getDimension(R.dimen.margin_horizontal_product_list_item).toInt()
            )
        )
        viewBinding.rvProducts.layoutManager = LinearLayoutManager(requireContext())

        val productAdapter = ProductAdapter()
        viewBinding.rvProducts.adapter = productAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

}