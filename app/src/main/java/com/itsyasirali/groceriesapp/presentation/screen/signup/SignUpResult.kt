package com.itsyasirali.groceriesapp.presentation.screen.signup

sealed class SignUpResult {
    object Loading : SignUpResult()
    data class Success(val data: Unit = Unit) : SignUpResult()
    data class Error(val exception: Throwable) : SignUpResult()
}
