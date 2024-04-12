package com.softyorch.firestoreadvanced.ui.addProduct

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.softyorch.firestoreadvanced.databinding.ActivityAddProductBinding
import kotlinx.coroutines.launch

class AddProductActivity : AppCompatActivity() {

    companion object {
        fun create(context: Context): Intent = Intent(context, AddProductActivity::class.java)
    }

    private lateinit var binding: ActivityAddProductBinding
    private val viewModel: AddProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        initListeners()
        initUiState()
    }

    private fun initUiState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->

                }
            }
        }
    }

    private fun initListeners() {

    }
}