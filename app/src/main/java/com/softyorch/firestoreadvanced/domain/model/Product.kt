package com.softyorch.firestoreadvanced.domain.model

data class Product(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val price: String
)
