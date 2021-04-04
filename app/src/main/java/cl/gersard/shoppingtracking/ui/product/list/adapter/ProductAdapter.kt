package cl.gersard.shoppingtracking.ui.product.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import cl.gersard.shoppingtracking.databinding.RowProductBinding
import cl.gersard.shoppingtracking.domain.product.Product
import cl.gersard.shoppingtracking.ui.diffutil.CustomDiffCallback
import cl.gersard.shoppingtracking.ui.diffutil.DiffUtilHelperModel

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
//            val startIndex = this.products.size
//            this.products.addAll(products)
//            notifyItemRangeInserted(startIndex, this.products.size)
            val diffCalback = CustomDiffCallback<DiffUtilHelperModel>(this.products,products)
            val diffResult = DiffUtil.calculateDiff(diffCalback)
            this.products.clear()
            this.products.addAll(products)
            diffResult.dispatchUpdatesTo(this)
        }
    }

    inner class ProductViewHolder(private val viewBinding: RowProductBinding) : RecyclerView.ViewHolder(viewBinding.root) {

        fun bindData(product: Product) {
            with(viewBinding) {
                tvBrandName.text = product.brand.name
                tvProductName.text = product.name
                tvLastPurchasePrice.text = product.purchases?.firstOrNull()?.let {
                    "$${it.price}"
                } ?: "---"

            }
        }
    }


}