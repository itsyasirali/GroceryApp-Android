package com.itsyasirali.groceriesapp.domain.usecase.deletecartusecase

import com.itsyasirali.groceriesapp.data.repository.Repository
import com.itsyasirali.groceriesapp.domain.model.ProductItem

class DeleteCartUseCase(
    private val repository: Repository
) {

    suspend operator fun invoke(productItem: ProductItem) = repository.deleteCart(productItem)

}