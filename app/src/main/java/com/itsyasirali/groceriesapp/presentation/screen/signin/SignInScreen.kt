package com.itsyasirali.groceriesapp.presentation.screen.signin

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.itsyasirali.groceriesapp.presentation.screen.splash.Splash
import com.itsyasirali.groceriesapp.utils.SharedPrefManager

@Composable
fun SignInScreen(
    viewModel: SignInViewModel = viewModel(),
    onSignInSuccess: () -> Unit,
    onNavigateToSignUp: () -> Unit
) {
    val context = LocalContext.current
    val signInState by viewModel.signInResult.collectAsState()
    val isLoading = signInState is SignInResult.Loading

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val sharedPrefManager = remember { SharedPrefManager(context) }

    LaunchedEffect(signInState) {
        when (val result = signInState) {
            is SignInResult.Success -> {
                sharedPrefManager.saveUser(result.user)
                Toast.makeText(context, "Welcome, ${result.user.name}!", Toast.LENGTH_SHORT).show()
                onSignInSuccess()
                viewModel.clearState()
            }
            is SignInResult.Error -> {
                Toast.makeText(context, "Sign-in failed: ${result.exception.message}", Toast.LENGTH_LONG).show()
                viewModel.clearState()
            }
            else -> Unit
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Sign In",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Welcome back! Please enter your credentials.",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = { Text(color = Color.Gray, text = "Email address") },
            textStyle = LocalTextStyle.current.copy(color = Color.Black),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text(color = Color.Gray, text = "Password") },
            textStyle = LocalTextStyle.current.copy(color = Color.Black),
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                viewModel.signIn(email, password)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4CAF50))
        ) {
            Text("Sign In", fontSize = 16.sp, color = Color.White)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Don't have an account? ",
                fontSize = 14.sp,
                color = Color.Gray
            )
            Text(
                text = "Sign up",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF1B73E8),
                modifier = Modifier.clickable { onNavigateToSignUp() }
            )
        }
    }
    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x80000000)),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    SignInScreen(
        onSignInSuccess = {},
        onNavigateToSignUp = {}
    )
}