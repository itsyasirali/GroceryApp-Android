package com.itsyasirali.groceriesapp.navigation.screen

sealed class Screen(val route: String) {

    object Splash : Screen("splash_screen")

    object OnBoarding : Screen("on_boarding_screen")

    object SignIn : Screen("sign_in_screen")

    object SignUp : Screen("sign_up_screen")

    object Main : Screen("main_screen")

    object Search : Screen("search_screen")

    object Details : Screen("details_screen/{productId}") {
        fun passProductId(productId: Int): String = "details_screen/$productId"
    }
}
