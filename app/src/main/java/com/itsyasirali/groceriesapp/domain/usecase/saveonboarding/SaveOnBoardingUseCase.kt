package com.itsyasirali.groceriesapp.domain.usecase.saveonboarding

import com.itsyasirali.groceriesapp.data.repository.Repository

class SaveOnBoardingUseCase(
    private val repository: Repository
) {

    suspend operator fun invoke(isCompleted: Boolean) {
        repository.saveOnBoardingState(isCompleted = isCompleted)
    }

}