package com.itsyasirali.groceriesapp.presentation.screen.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itsyasirali.groceriesapp.data.repository.FbRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SignupViewModel(private val repo: FbRepo = FbRepo()) : ViewModel() {

    private val _signUpState = MutableStateFlow<SignUpResult?>(null)
    val signUpState: StateFlow<SignUpResult?> = _signUpState

    fun registerUser(
        name: String,
        email: String,
        phone: String,
        address: String,
        password: String
    ) {
        viewModelScope.launch {
            _signUpState.value = SignUpResult.Loading
            val result = repo.registerUser(name, email, phone, address, password)
            _signUpState.value = result.fold(
                onSuccess = { SignUpResult.Success() },
                onFailure = { SignUpResult.Error(it) }
            )
        }
    }

    fun clearState() {
        _signUpState.value = null
    }
}
