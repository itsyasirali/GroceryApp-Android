package com.itsyasirali.groceriesapp.navigation.graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.itsyasirali.groceriesapp.navigation.screen.Screen
import com.itsyasirali.groceriesapp.presentation.screen.MainScreen
import com.itsyasirali.groceriesapp.presentation.screen.onboarding.OnBoardingScreen
import com.itsyasirali.groceriesapp.presentation.screen.signin.SignInScreen
import com.itsyasirali.groceriesapp.presentation.screen.signup.SignUpScreen
import com.itsyasirali.groceriesapp.presentation.screen.splash.SplashScreen
import com.itsyasirali.groceriesapp.utils.SharedPrefManager

@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(navController)
        }

        composable(route = Screen.OnBoarding.route) {
            OnBoardingScreen(navController =navController)
        }

        composable(route = Screen.SignIn.route) {
            SignInScreen(
                onSignInSuccess = {
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.SignIn.route) { inclusive = true }
                    }
                },
                onNavigateToSignUp = {
                    navController.navigate(Screen.SignUp.route)
                }
            )
        }

        composable(route = Screen.SignUp.route) {
            SignUpScreen(
                onSignUpSuccess = {
                    navController.navigate(Screen.SignIn.route) {
                        popUpTo(Screen.SignUp.route) { inclusive = true }
                    }
                },
                onNavigateToSignIn = {
                    navController.navigate(Screen.SignIn.route) {
                        popUpTo(Screen.SignUp.route) { inclusive = true }
                    }
                }
            )
        }

        composable(route = Screen.Main.route) {
            MainScreen()
        }
    }
}
