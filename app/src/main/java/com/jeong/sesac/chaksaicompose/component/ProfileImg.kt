package com.jeong.sesac.chaksaicompose.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import com.jeong.sesac.chaksaicompose.R

@Composable
fun ProfileSmall(img: Int) {
    Image(
        painter = painterResource(img),
        contentDescription = "profile_img",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .width(dimensionResource(R.dimen.profileSmall))
            .height(dimensionResource(R.dimen.profileSmall))
            .clip(CircleShape)
    )
}