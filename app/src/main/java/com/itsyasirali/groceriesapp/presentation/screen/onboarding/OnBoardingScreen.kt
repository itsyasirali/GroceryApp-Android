package com.itsyasirali.groceriesapp.presentation.screen.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.itsyasirali.groceriesapp.navigation.graph.Graph
import com.itsyasirali.groceriesapp.navigation.screen.Screen
import com.itsyasirali.groceriesapp.ui.theme.*
import com.itsyasirali.groceriesapp.utils.SharedPrefManager

@Composable
fun OnBoardingScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onBoardingViewModel: OnBoardingViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    OnBoarding(
        modifier = modifier.fillMaxSize(),
        onClick = {
            onBoardingViewModel.saveOnBoardingState(isCompleted = true)
            navController.popBackStack()

            val isLoggedIn = SharedPrefManager(context).isLoggedIn()
            navController.navigate(if (isLoggedIn) Screen.Main.route else Screen.SignIn.route) {
                popUpTo(Screen.OnBoarding.route) { inclusive = true }
            }
        }
    )
}

@Composable
fun OnBoarding(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = com.itsyasirali.groceriesapp.R.drawable.img_onboarding),
            contentDescription = stringResource(com.itsyasirali.groceriesapp.R.string.image_on_boarding),
            contentScale = ContentScale.Crop
        )
        Surface(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = DIMENS_90dp),
            color = Color.Transparent
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = com.itsyasirali.groceriesapp.R.string.welcome_to_store),
                    fontFamily = GilroyFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = TEXT_SIZE_49sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = stringResource(com.itsyasirali.groceriesapp.R.string.desc_welcome),
                    fontFamily = GilroyFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = TEXT_SIZE_16sp,
                    color = GrayTextColor,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.height(DIMENS_40dp))
                Button(
                    onClick = {
                        onClick.invoke()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(height = DIMENS_68dp)
                        .padding(start = DIMENS_16dp, end = DIMENS_16dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Green),
                    shape = RoundedCornerShape(DIMENS_12dp),
                ) {
                    Text(
                        text = stringResource(com.itsyasirali.groceriesapp.R.string.get_started),
                        fontFamily = GilroyFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = TEXT_SIZE_18sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun OnBoardingPreview() {
    OnBoarding(onClick = {})
}