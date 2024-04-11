package com.softyorch.firestoreadvanced.data.response

import com.softyorch.firestoreadvanced.domain.model.Product

data class ProductResponse(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val price: String = "",
) {
    companion object {
        fun ProductResponse.toDomain(): Product = Product(id, name, description, imageUrl, price)
    }
}
