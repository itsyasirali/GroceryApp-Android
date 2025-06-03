package com.itsyasirali.groceriesapp.presentation.screen.about

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import com.itsyasirali.groceriesapp.presentation.common.card.ProfileCard
import com.itsyasirali.groceriesapp.presentation.common.content.ListContentAbout
import com.itsyasirali.groceriesapp.ui.theme.*
import com.itsyasirali.groceriesapp.utils.SharedPrefManager
import com.itsyasirali.groceriesapp.navigation.screen.Screen

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val context = LocalContext.current
    val sharedPrefManager = SharedPrefManager(context)

    Column(
        modifier = modifier
            .padding(top = DIMENS_24dp)
            .fillMaxSize()
    ) {
        ProfileCard()
        ListContentAbout { title ->
            when (title) {
                "My Details" -> navController.navigate("my_details")
            }
        }
        Spacer(modifier = Modifier.height(DIMENS_32dp))

        ButtonLogout {
            sharedPrefManager.clearUser()
            /*navController.navigate(Screen.SignIn.route) {
                popUpTo(Screen.Main.route) { inclusive = true }
            }*/
        }
    }
}

@Composable
fun ButtonLogout(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .padding(DIMENS_16dp)
            .height(DIMENS_48dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(DIMENS_16dp),
        elevation = ButtonDefaults.elevation(DIMENS_2dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = GrayBackground),
        onClick = { onClick() }
    ) {
        Icon(
            imageVector = Icons.Default.ExitToApp,
            contentDescription = stringResource(com.itsyasirali.groceriesapp.R.string.logout),
            tint = Green
        )
        Spacer(modifier = Modifier.width(DIMENS_8dp))
        Text(
            text = stringResource(id = com.itsyasirali.groceriesapp.R.string.logout),
            color = Green,
            textAlign = TextAlign.Center,
            fontFamily = GilroyFontFamily,
            fontWeight = FontWeight.SemiBold,
        )
    }
}
