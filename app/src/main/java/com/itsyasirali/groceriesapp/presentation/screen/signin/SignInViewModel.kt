package com.itsyasirali.groceriesapp.presentation.screen.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itsyasirali.groceriesapp.data.repository.FbRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SignInViewModel(private val repo: FbRepo = FbRepo()) : ViewModel() {

    private val _signInResult = MutableStateFlow<SignInResult?>(null)
    val signInResult: StateFlow<SignInResult?> = _signInResult

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            _signInResult.value = SignInResult.Loading

            val result = repo.loginUser(email, password)

            _signInResult.value = result.fold(
                onSuccess = { user -> SignInResult.Success(user) },
                onFailure = { error -> SignInResult.Error(error) }
            )
        }
    }

    fun clearState() {
        _signInResult.value = null
    }
}
