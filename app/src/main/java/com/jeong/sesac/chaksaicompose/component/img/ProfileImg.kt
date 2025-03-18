package com.jeong.sesac.chaksaicompose.component.img

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import coil3.compose.AsyncImage
import com.jeong.sesac.chaksaicompose.R
import com.jeong.sesac.chaksaicompose.common.coil.CoilImgRequest

@Composable
fun ProfileSmall(img: String) {
    AsyncImage(
        modifier = Modifier
            .width(dimensionResource(R.dimen.profileSmall))
            .height(dimensionResource(R.dimen.profileSmall))
            .clip(CircleShape),
        model = CoilImgRequest.getImgRequest(img),
        contentScale = ContentScale.FillBounds,
        contentDescription = stringResource(R.string.profileImage),
        onState = { state -> CoilImgRequest.coilCallbackLog(img, state) }
    )
}