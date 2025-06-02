package com.itsyasirali.groceriesapp.domain.usecase

import com.itsyasirali.groceriesapp.domain.usecase.addcartusecase.AddCartUseCase
import com.itsyasirali.groceriesapp.domain.usecase.deletecartusecase.DeleteCartUseCase
import com.itsyasirali.groceriesapp.domain.usecase.getallcartusecase.GetAllCartUseCase
import com.itsyasirali.groceriesapp.domain.usecase.getallproduct.GetAllProductUseCase
import com.itsyasirali.groceriesapp.domain.usecase.getselectedproduct.GetSelectedProductUseCase
import com.itsyasirali.groceriesapp.domain.usecase.readonboarding.ReadOnBoardingUseCase
import com.itsyasirali.groceriesapp.domain.usecase.saveonboarding.SaveOnBoardingUseCase
import com.itsyasirali.groceriesapp.domain.usecase.saveproductusecase.InsertProductsUseCase
import com.itsyasirali.groceriesapp.domain.usecase.searchproductusecase.SearchProductUseCase

data class UseCases(
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val insertProductsUseCase: InsertProductsUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase,
    val getSelectedProductUseCase: GetSelectedProductUseCase,
    val getAllProductUseCase: GetAllProductUseCase,
    val getAllCartUseCase: GetAllCartUseCase,
    val addCartUseCase: AddCartUseCase,
    val deleteCart: DeleteCartUseCase,
    val searchProductUseCase: SearchProductUseCase
)