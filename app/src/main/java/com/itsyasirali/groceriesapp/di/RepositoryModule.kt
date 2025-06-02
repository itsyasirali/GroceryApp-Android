package com.itsyasirali.groceriesapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.itsyasirali.groceriesapp.data.repository.OnBoardingOperationImpl
import com.itsyasirali.groceriesapp.data.repository.Repository
import com.itsyasirali.groceriesapp.domain.repository.OnBoardingOperations
import com.itsyasirali.groceriesapp.domain.usecase.UseCases
import com.itsyasirali.groceriesapp.domain.usecase.addcartusecase.AddCartUseCase
import com.itsyasirali.groceriesapp.domain.usecase.deletecartusecase.DeleteCartUseCase
import com.itsyasirali.groceriesapp.domain.usecase.getallcartusecase.GetAllCartUseCase
import com.itsyasirali.groceriesapp.domain.usecase.getallproduct.GetAllProductUseCase
import com.itsyasirali.groceriesapp.domain.usecase.getselectedproduct.GetSelectedProductUseCase
import com.itsyasirali.groceriesapp.domain.usecase.readonboarding.ReadOnBoardingUseCase
import com.itsyasirali.groceriesapp.domain.usecase.saveonboarding.SaveOnBoardingUseCase
import com.itsyasirali.groceriesapp.domain.usecase.saveproductusecase.InsertProductsUseCase
import com.itsyasirali.groceriesapp.domain.usecase.searchproductusecase.SearchProductUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStoreOperation(
        @ApplicationContext context: Context
    ): OnBoardingOperations = OnBoardingOperationImpl(context = context)

    @Provides
    @Singleton
    fun provideUseCase(repository: Repository): UseCases {
        return UseCases(
            saveOnBoardingUseCase = SaveOnBoardingUseCase(repository),
            insertProductsUseCase = InsertProductsUseCase(repository),
            readOnBoardingUseCase = ReadOnBoardingUseCase(repository),
            getSelectedProductUseCase = GetSelectedProductUseCase(repository),
            getAllProductUseCase = GetAllProductUseCase(repository),
            getAllCartUseCase = GetAllCartUseCase(repository),
            addCartUseCase = AddCartUseCase(repository),
            deleteCart = DeleteCartUseCase(repository),
            searchProductUseCase = SearchProductUseCase(repository)
        )
    }

}