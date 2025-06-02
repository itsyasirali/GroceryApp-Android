package com.itsyasirali.groceriesapp.domain.usecase.getallcartusecase

import com.itsyasirali.groceriesapp.data.repository.Repository
import com.itsyasirali.groceriesapp.domain.model.ProductItem
import kotlinx.coroutines.flow.Flow

class GetAllCartUseCase(
    private val repository: Repository
) {

    operator fun invoke(isCart: Boolean): Flow<List<ProductItem>> =
        repository.getAllProductCart(isCart)

}