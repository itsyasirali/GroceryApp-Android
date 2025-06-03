package com.itsyasirali.groceriesapp.presentation.screen.signin

import com.itsyasirali.groceriesapp.domain.model.User

sealed class SignInResult {
    object Loading : SignInResult()
    data class Success(val user: User) : SignInResult()
    data class Error(val exception: Throwable) : SignInResult()
}
