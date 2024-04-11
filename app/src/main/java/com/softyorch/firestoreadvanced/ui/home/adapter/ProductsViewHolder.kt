package com.softyorch.firestoreadvanced.ui.home.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.softyorch.firestoreadvanced.databinding.ItemProductBinding
import com.softyorch.firestoreadvanced.domain.model.Product

class ProductsViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemProductBinding.bind(view)
    fun render(product: Product) {
        binding.apply {
            product.apply {
                Glide.with(ivProduct.context).load(imageUrl).into(ivProduct)
                tvTitle.text = title
                tvDescription.text = description
                tvPrice.text = "$price€"
            }
        }
    }
}