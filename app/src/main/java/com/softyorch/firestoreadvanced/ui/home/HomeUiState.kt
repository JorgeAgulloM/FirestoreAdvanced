package com.softyorch.firestoreadvanced.ui.home

import com.softyorch.firestoreadvanced.domain.model.Product

data class HomeUiState(
    val lastProduct: Product? = null,
    val products: List<Product> = emptyList(),
    val topProducts: List<Product> = emptyList(),
)
