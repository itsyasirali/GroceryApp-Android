package com.itsyasirali.groceriesapp.domain.usecase.addcartusecase

import com.itsyasirali.groceriesapp.data.repository.Repository
import com.itsyasirali.groceriesapp.domain.model.ProductItem

class AddCartUseCase(
    private val repository: Repository
) {

    suspend operator fun invoke(productItem: ProductItem) = repository.addCart(productItem)

}