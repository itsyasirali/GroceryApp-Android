package com.itsyasirali.groceriesapp.presentation.screen.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.itsyasirali.groceriesapp.domain.model.ProductItem
import com.itsyasirali.groceriesapp.domain.usecase.UseCases
import com.itsyasirali.groceriesapp.utils.Constants.PRODUCT_ARGUMENT_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCases: UseCases,
    saveStatedHandle: SavedStateHandle
) : ViewModel() {

    private val _selectedProduct: MutableStateFlow<ProductItem?> = MutableStateFlow(null)
    val selectedProduct: StateFlow<ProductItem?> = _selectedProduct

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val productId = saveStatedHandle.get<Int>(PRODUCT_ARGUMENT_KEY) ?: 0
            _selectedProduct.value =
                useCases.getSelectedProductUseCase.invoke(productId = productId)
        }
    }

    fun addCart(productItem: ProductItem) = viewModelScope.launch {
        useCases.addCartUseCase.invoke(productItem)
    }

}