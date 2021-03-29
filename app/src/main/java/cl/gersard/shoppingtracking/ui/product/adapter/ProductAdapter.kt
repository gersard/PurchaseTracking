package cl.gersard.shoppingtracking.ui.product.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.gersard.shoppingtracking.databinding.RowProductBinding
import cl.gersard.shoppingtracking.domain.product.Product

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private val products = mutableListOf<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProductViewHolder(
        RowProductBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) = holder.bindData(products[position])

    override fun getItemCount() = products.size

    fun addProducts(products: List<Product>) {
        if (products.isNotEmpty()) {
            val startIndex = this.products.size
            this.products.addAll(products)
            notifyItemRangeInserted(startIndex, this.products.size)
        }
    }

    inner class ProductViewHolder(private val viewBinding: RowProductBinding) : RecyclerView.ViewHolder(viewBinding.root) {

        fun bindData(product: Product) {
            with(viewBinding) {
                tvBrandName.text = product.brand.name
                tvProductName.text = product.name
                tvLastPurchasePrice.text = product.purchases.firstOrNull()?.let {
                    "$${it.price}"
                } ?: "---"

            }
        }
    }


}