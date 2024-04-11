package com.softyorch.firestoreadvanced.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softyorch.firestoreadvanced.data.network.DatabaseService
import com.softyorch.firestoreadvanced.domain.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: DatabaseService) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        getData()
    }

    private fun getData() {
        getLastProduct()
        getAllProducts()
    }

    private fun getAllProducts() {
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) {
                repository.getAllProducts()
            }
            _uiState.update { it.copy(products = response) }
            getTopProducts(response)
        }
    }

    private fun getTopProducts(products: List<Product>) {
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) {
                repository.getTopProducts()
            }

            val topProducts = products.filter { response.contains(it.id) }
            _uiState.update { it.copy(topProducts = topProducts) }
        }
    }

    private fun getLastProduct() {
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) {
                repository.getLastProduct()
            }

            _uiState.update { it.copy(lastProduct = response) }
        }
    }

}