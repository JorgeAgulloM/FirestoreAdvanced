package com.softyorch.firestoreadvanced.ui.addProduct

data class AddProductState(
    val name: String = "",
    val description: String = "",
    val price: String = "",
    val imageUrl: String = "",
    val isLoading: Boolean = false,
)
