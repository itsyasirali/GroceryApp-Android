package com.itsyasirali.groceriesapp.presentation.common.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.itsyasirali.groceriesapp.ui.theme.*
import com.itsyasirali.groceriesapp.utils.SharedPrefManager

@Composable
fun ProfileCard(
    modifier: Modifier = Modifier
) {
    val mContext = LocalContext.current
    val sharedPrefManager = remember { SharedPrefManager(mContext) }

    Row(
        modifier = modifier
            .padding(start = DIMENS_16dp, end = DIMENS_16dp)
            .fillMaxWidth()
    ) {
        Card(
            border = BorderStroke(width = DIMENS_2dp, color = Black),
            shape = RoundedCornerShape(DIMENS_40dp),
        ) {
            Image(
                modifier = Modifier.height(DIMENS_80dp),
                painter = painterResource(id = com.itsyasirali.groceriesapp.R.drawable.img_profile),
                contentDescription = stringResource(com.itsyasirali.groceriesapp.R.string.image_profile)
            )
        }

        Spacer(modifier = Modifier.width(DIMENS_16dp))

        Column(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically),
        ) {
            Text(
                text = sharedPrefManager.getUser()!!.name,
                fontFamily = GilroyFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = TEXT_SIZE_18sp,
                color = Black
            )
            Text(
                text = sharedPrefManager.getUser()!!.email,
                fontFamily = GilroyFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = TEXT_SIZE_12sp,
                color = GraySecondTextColor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileCardPreview() {
    ProfileCard()
}