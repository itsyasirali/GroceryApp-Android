package com.itsyasirali.groceriesapp.presentation.screen.signup

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SignUpScreen(
    viewModel: SignupViewModel = viewModel(),
    onSignUpSuccess: () -> Unit,
    onNavigateToSignIn: () -> Unit
) {
    val context = LocalContext.current
    val signUpState by viewModel.signUpState.collectAsState()
    val isLoading = signUpState is SignUpResult.Loading

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LaunchedEffect(signUpState) {
        when (val result = signUpState) {
            is SignUpResult.Success -> {
                Toast.makeText(context, "Registration successful!", Toast.LENGTH_SHORT).show()
                onSignUpSuccess()
                viewModel.clearState()
            }
            is SignUpResult.Error -> {
                Toast.makeText(context, "Error: ${result.exception.message}", Toast.LENGTH_LONG).show()
                viewModel.clearState()
            }
            else -> Unit
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Create Account",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Join us by filling out the form below.",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            placeholder = { Text(color = Color.Gray, text = "Full Name") },
            textStyle = LocalTextStyle.current.copy(color = Color.Black),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = { Text(color = Color.Gray, text = "Email Address") },
            textStyle = LocalTextStyle.current.copy(color = Color.Black),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            placeholder = { Text(color = Color.Gray, text = "Phone Number") },
            textStyle = LocalTextStyle.current.copy(color = Color.Black),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = address,
            onValueChange = { address = it },
            placeholder = { Text(color = Color.Gray, text = "Address") },
            textStyle = LocalTextStyle.current.copy(color = Color.Black),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

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
                viewModel.registerUser(name, email, phone, address, password)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4CAF50))
        ) {
            Text("Sign Up", fontSize = 16.sp, color = Color.White)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Already have an account? ", fontSize = 14.sp, color = Color.Gray)
            Text(
                text = "Sign in",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF1B73E8),
                modifier = Modifier.clickable { onNavigateToSignIn() }
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
fun SignUpScreenPreview() {
    SignUpScreen(
        onSignUpSuccess = {},
        onNavigateToSignIn = {}
    )
}
