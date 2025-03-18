package com.jeong.sesac.chaksaicompose.component.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil3.compose.AsyncImage
import com.jeong.sesac.chaksaicompose.R
import com.jeong.sesac.chaksaicompose.common.coil.CoilImgRequest
import com.jeong.sesac.chaksaicompose.ui.theme.AppTheme
import com.jeong.sesac.feature.model.PostWithUser


@Composable
fun TitleUi(title: Int, onMoreClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            stringResource(title),
            style = AppTheme.typography.titleMedium
        )
        Row(
            modifier = Modifier.fillMaxWidth(fraction = 0.25f)
                .clickable { onMoreClick() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,

        ) {
            Text(
                stringResource(R.string.more_content),
                style = AppTheme.typography.titleSmall,
                color = Color.Gray,

            )
            Icon(
                painter = painterResource(R.drawable.ic_right_arrow),
                contentDescription = stringResource(R.string.icon_more_content),
                modifier = Modifier.size(AppTheme.size.normal),
                tint = Color.Gray,
            )
        }
    }
}

@Composable
fun LazyColUI(data: List<PostWithUser>, onPostClick: (PostWithUser) -> Unit) {
    LazyRow(
        modifier = Modifier.fillMaxWidth().padding(vertical = dimensionResource(R.dimen.cardPadding)),
    ) {
        items(data) { item ->
            ListItemUI(data = item, onClick = onPostClick)
        }
    }
}


@Composable
fun ListItemUI(data: PostWithUser, onClick: (PostWithUser) -> Unit) {
    Card(
        modifier = Modifier
            .width(dimensionResource(R.dimen.cardMedium))
            .height(dimensionResource(R.dimen.cardMedium))
            .padding(dimensionResource(R.dimen.cardPadding))
            .clickable { onClick(data) },
        shape = RoundedCornerShape(AppTheme.size.normal),
        elevation = CardDefaults.cardElevation(
            defaultElevation = AppTheme.size.xs,
            pressedElevation = AppTheme.size.small
        ),

    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = CoilImgRequest.getImgRequest(data.image),
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(R.string.post_img_desc),
            onState = { state -> CoilImgRequest.coilCallbackLog(data.image, state) }
        )
    }
}