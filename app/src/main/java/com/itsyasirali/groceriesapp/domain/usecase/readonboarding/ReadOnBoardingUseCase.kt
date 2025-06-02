package com.itsyasirali.groceriesapp.domain.usecase.readonboarding

import com.itsyasirali.groceriesapp.data.repository.Repository
import kotlinx.coroutines.flow.Flow

class ReadOnBoardingUseCase(private val repository: Repository) {

    operator fun invoke(): Flow<Boolean> = repository.readOnBoardingState()

}