package com.itsyasirali.groceriesapp.domain.usecase.getallproduct

import com.itsyasirali.groceriesapp.data.repository.Repository
import com.itsyasirali.groceriesapp.domain.model.ProductItem
import kotlinx.coroutines.flow.Flow

class GetAllProductUseCase(
    private val repository: Repository
) {

    operator fun invoke(): Flow<List<ProductItem>> = repository.getAllProduct()

}