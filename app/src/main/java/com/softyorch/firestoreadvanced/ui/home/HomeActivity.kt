package com.softyorch.firestoreadvanced.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.softyorch.firestoreadvanced.R
import com.softyorch.firestoreadvanced.databinding.ActivityHomeBinding
import com.softyorch.firestoreadvanced.domain.model.Product
import com.softyorch.firestoreadvanced.ui.home.adapter.ProductsAdapter
import com.softyorch.firestoreadvanced.ui.home.adapter.SpacingDecorator
import com.softyorch.firestoreadvanced.ui.home.adapter.TopProductsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var productsAdapter: ProductsAdapter
    private lateinit var topProductsAdapter: TopProductsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        initUi()
    }

    private fun initUi() {
        initShimmers()
        initListeners()
        initList()
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

    private fun initShimmers() {
        binding.apply {
            viewLastProductShimmer.root.startShimmer()
            rvTopProductsShimmer.startShimmer()
        }
    }

    private fun initList() {
        productsAdapter = ProductsAdapter()
        binding.rvProducts.apply {
            layoutManager = GridLayoutManager(context, 2)
            addItemDecoration(SpacingDecorator(16))
            adapter = productsAdapter
        }

        topProductsAdapter = TopProductsAdapter()
        binding.rvTopProducts.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(SpacingDecorator(16))
            adapter = topProductsAdapter
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
        binding.apply {
            viewLastProduct.apply {
                lastProduct.apply {
                    tvTitle.text = title
                    tvDescription.text = description
                    Glide.with(this@HomeActivity)
                        .load(imageUrl)
                        .placeholder(R.drawable.ic_place_holder)
                        .into(ivLastProduct)
                }
                root.isVisible = true
            }
            viewLastProductShimmer.root.stopShimmer()
        }
    }

    private fun renderTopProducts(topProducts: List<Product>) {
        if (topProducts.isEmpty()) return
        topProductsAdapter.updateList(topProducts)
        binding.rvTopProductsShimmer.apply {
            isVisible = false
            stopShimmer()
        }
    }

    private fun renderProducts(products: List<Product>) {
        productsAdapter.updateList(products)
    }
}
