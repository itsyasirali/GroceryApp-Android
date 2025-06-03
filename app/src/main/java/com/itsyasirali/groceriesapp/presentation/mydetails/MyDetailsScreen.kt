package com.itsyasirali.groceriesapp.presentation.mydetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.itsyasirali.groceriesapp.utils.SharedPrefManager

@Composable
fun MyDetailsScreen() {
    val context = LocalContext.current
    val sharedPrefManager = remember { SharedPrefManager(context) }

    val name by remember { mutableStateOf(sharedPrefManager.getUser()!!.name) }
    val email by remember { mutableStateOf(sharedPrefManager.getUser()!!.email) }
    val phone by remember { mutableStateOf(sharedPrefManager.getUser()!!.phone) }
    val address by remember { mutableStateOf(sharedPrefManager.getUser()!!.address) }

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text("My Profile", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colors.primary)
            Spacer(modifier = Modifier.height(24.dp))

            UserInfoCard(label = "Name", value = name)
            UserInfoCard(label = "Email", value = email)
            UserInfoCard(label = "Phone", value = phone)
            UserInfoCard(label = "Address", value = address)
        }
    }
}

@Composable
fun UserInfoCard(label: String, value: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = label, fontSize = 14.sp, color = MaterialTheme.colors.primary)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = value, fontSize = 16.sp, fontWeight = FontWeight.Medium, color = MaterialTheme.colors.onSurface)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MyDetailsPreview() {
    MyDetailsScreen()
}
