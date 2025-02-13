package com.jeong.sesac.chaksaicompose.component.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import com.jeong.sesac.chaksaicompose.R
import com.jeong.sesac.chaksaicompose.ui.theme.AppTheme
import com.jeong.sesac.feature.model.NoteWithUser


@Composable
fun TitleUi(title: Int) {
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
            modifier = Modifier.fillMaxWidth(fraction = 0.25f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly

        ) {
            Text(
                stringResource(R.string.more_content),
                style = AppTheme.typography.titleSmall,
                color = Color.Gray
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
fun LazyColUI(data: List<NoteWithUser>, onClick: (NoteWithUser) -> Unit) {
    LazyRow(
        modifier = Modifier.fillMaxWidth().padding(vertical = dimensionResource(R.dimen.cardPadding)),
    ) {
        items(data) { item ->
            ListItemUI(data = item, onClick = onClick)
        }
    }
}


@Composable
fun ListItemUI(data: NoteWithUser, onClick: (NoteWithUser) -> Unit) {
    Card(
        modifier = Modifier
            .width(dimensionResource(R.dimen.cardMedium))
            .height(dimensionResource(R.dimen.cardMedium))
            .padding(dimensionResource(R.dimen.cardPadding)),
        shape = RoundedCornerShape(AppTheme.size.normal),
        elevation = CardDefaults.cardElevation(
            defaultElevation = AppTheme.size.xs,
            pressedElevation = AppTheme.size.small
        )

    ) {

        Image(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(),
            painter = painterResource(data.image),
            contentDescription = "note_image",
            contentScale = ContentScale.Crop
        )

    }
}