package com.softyorch.firestoreadvanced.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.softyorch.firestoreadvanced.R
import com.softyorch.firestoreadvanced.domain.model.Product

class ProductsAdapter(
    private var products: List<Product> = emptyList()
) : RecyclerView.Adapter<ProductsViewHolder>() {

    fun updateList(products: List<Product>) {
        this.products = products

        //Modificar con un DiffUtils

        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductsViewHolder(view)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.render(products[position])
    }
}