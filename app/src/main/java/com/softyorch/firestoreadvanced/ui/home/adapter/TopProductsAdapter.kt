package com.softyorch.firestoreadvanced.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.softyorch.firestoreadvanced.R
import com.softyorch.firestoreadvanced.domain.model.Product

class TopProductsAdapter(
    private var products: List<Product> = emptyList()
) : RecyclerView.Adapter<TopProductsViewHolder>() {

    fun updateList(topProducts: List<Product>) {
        this.products = topProducts
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopProductsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_top_product, parent, false)
        return TopProductsViewHolder(view)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: TopProductsViewHolder, position: Int) {
        holder.render(products[position])
    }
}