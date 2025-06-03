package com.itsyasirali.groceriesapp.presentation.screen.home

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.itsyasirali.groceriesapp.domain.model.ProductItem
import com.itsyasirali.groceriesapp.navigation.screen.Screen
import com.itsyasirali.groceriesapp.presentation.common.content.ListContentProduct
import com.itsyasirali.groceriesapp.presentation.component.SearchViewBar
import com.itsyasirali.groceriesapp.presentation.component.SliderBanner
import com.itsyasirali.groceriesapp.ui.theme.*
import com.itsyasirali.groceriesapp.utils.SharedPrefManager
import com.itsyasirali.groceriesapp.utils.showToastShort

@ExperimentalPagerApi
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val mContext = LocalContext.current
    val searchQuery by homeViewModel.searchQuery
    val allProducts by homeViewModel.productList.collectAsState()
    val sharedPrefManager = remember { SharedPrefManager(mContext) }

    Scaffold { padding ->
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .padding(padding)
        ) {
            HeaderLocationHome(location = sharedPrefManager.getUser()!!.address)

            SearchViewBar(
                hint = stringResource(id = com.itsyasirali.groceriesapp.R.string.search_store),
                query = searchQuery,
                onValueChange = {
                    if (it.isNotEmpty()) navController.navigate(Screen.Search.route)
                }
            )

            SliderBanner()

            ListContentProduct(
                title = stringResource(id = com.itsyasirali.groceriesapp.R.string.exclusive_offer),
                products = allProducts,
                navController = navController,
                onClickToCart = { productItem ->
                    clickToCart(mContext, productItem, homeViewModel)
                }
            )

            Spacer(modifier = Modifier.height(DIMENS_24dp))

            ListContentProduct(
                title = stringResource(id = com.itsyasirali.groceriesapp.R.string.best_selling),
                products = allProducts.sortedByDescending { it.id },
                navController = navController,
                onClickToCart = { productItem ->
                    clickToCart(mContext, productItem, homeViewModel)
                }
            )
        }
    }
}

@Composable
fun HeaderLocationHome(
    location: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(DIMENS_24dp))

        Icon(
            modifier = Modifier
                .size(DIMENS_24dp)
                .align(Alignment.CenterHorizontally),
            painter = painterResource(id = com.itsyasirali.groceriesapp.R.drawable.ic_nectar),
            contentDescription = stringResource(id = com.itsyasirali.groceriesapp.R.string.logo_app),
            tint = Color.Unspecified
        )

        Spacer(modifier = Modifier.height(DIMENS_8dp))

        Row {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = stringResource(com.itsyasirali.groceriesapp.R.string.image_location),
                tint = GrayThirdTextColor
            )
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = location,
                fontFamily = GilroyFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = TEXT_SIZE_12sp,
                color = GrayThirdTextColor
            )
        }
    }
}

fun clickToCart(context: Context, productItem: ProductItem, viewModel: HomeViewModel) {
    context.showToastShort("Success Add To Cart ${productItem.title}")
    viewModel.addCart(productItem.copy(isCart = true))
}

@Preview(showBackground = true)
@Composable
fun HeaderLocationHomePreview() {
    HeaderLocationHome(location = "Pakistan")
}