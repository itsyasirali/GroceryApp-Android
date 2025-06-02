package com.itsyasirali.groceriesapp.domain.usecase.searchproductusecase

import com.itsyasirali.groceriesapp.data.repository.Repository

class SearchProductUseCase(
    private val repository: Repository
) {

    operator fun invoke(query: String) = repository.searchProduct(query)

}