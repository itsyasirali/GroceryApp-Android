package com.itsyasirali.groceriesapp.domain.usecase.getselectedproduct

import com.itsyasirali.groceriesapp.data.repository.Repository
import com.itsyasirali.groceriesapp.domain.model.ProductItem

class GetSelectedProductUseCase(
    private val repository: Repository
) {

    suspend operator fun invoke(productId: Int): ProductItem {
        return repository.getSelectedProduct(productId = productId)
    }

}