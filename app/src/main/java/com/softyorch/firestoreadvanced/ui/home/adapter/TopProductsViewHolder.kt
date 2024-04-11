package com.softyorch.firestoreadvanced.ui.home.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.softyorch.firestoreadvanced.databinding.ItemTopProductBinding
import com.softyorch.firestoreadvanced.domain.model.Product

class TopProductsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemTopProductBinding.bind(view)

    fun render(product: Product) {
        binding.apply {
            product.apply {
                Glide.with(ivProduct.context).load(imageUrl).into(ivProduct)
                tvTitle.text = title
            }
        }
    }
}