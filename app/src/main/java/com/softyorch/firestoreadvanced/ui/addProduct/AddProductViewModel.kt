package com.softyorch.firestoreadvanced.ui.addProduct

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AddProductViewModel @Inject constructor(): ViewModel() {

    private val _uiState = MutableStateFlow(AddProductState())
    val uiState: StateFlow<AddProductState> = _uiState

    init {

    }
}