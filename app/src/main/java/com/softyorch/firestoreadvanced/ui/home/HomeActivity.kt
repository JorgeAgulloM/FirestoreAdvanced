package com.softyorch.firestoreadvanced.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.softyorch.firestoreadvanced.R
import com.softyorch.firestoreadvanced.databinding.ActivityHomeBinding
import com.softyorch.firestoreadvanced.domain.model.Product
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        initUi()
    }

    private fun initUi() {
        initListeners()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    renderLastProduct(state.lastProduct)
                    renderTopProducts(state.topProducts)
                    renderProducts(state.products)
                }
            }
        }
    }

    private fun initListeners() {
        binding.apply {
            viewToolbar.tvAddProduct.setOnClickListener {

            }
        }
    }

    private fun renderLastProduct(lastProduct: Product?) {
        if (lastProduct == null) return
        binding.viewLastProduct.apply {
            lastProduct.apply {
                tvTitle.text = title
                tvDescription.text = description
                Glide.with(this@HomeActivity)
                    .load(imageUrl)
                    .placeholder(R.drawable.ic_place_holder)
                    .into(ivLastProduct)
            }
        }
    }

    private fun renderTopProducts(topProducts: List<Product>) {

    }

    private fun renderProducts(products: List<Product>) {

    }
}
