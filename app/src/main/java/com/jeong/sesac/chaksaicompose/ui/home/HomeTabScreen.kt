package com.jeong.sesac.chaksaicompose.ui.home

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jeong.sesac.chaksaicompose.R
import com.jeong.sesac.chaksaicompose.common.mockNotes
import com.jeong.sesac.feature.model.NoteWithUser


@Composable
fun HomeTabScreen(
    navController: NavController
) {
    HomeTabContent()
}


@Composable
private fun HomeTabContent() {
    val mockData = mockNotes
    val composeCtx = LocalContext.current
    val onNoteClick = { noteData: NoteWithUser ->
        Toast.makeText(composeCtx, "${noteData.title}", Toast.LENGTH_SHORT).show()
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(Modifier.height(24.dp))

        Text(
            stringResource(R.string.main_header, "하겐다즈"),
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(Modifier.height(24.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                stringResource(R.string.main_weekly_popular_note_title),
                style = MaterialTheme.typography.titleMedium
            )
            Row(
                modifier = Modifier.fillMaxWidth(fraction = 0.25f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly

            ) {
                Text(
                    stringResource(R.string.more_content),
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.Gray
                )
                Icon(
                    painter = painterResource(R.drawable.ic_right_arrow),
                    contentDescription = stringResource(R.string.icon_more_content),
                    modifier = Modifier.size(14.dp),
                    tint = Color.Gray,
                )
            }
        }

        LazyRow(
            modifier = Modifier.fillMaxWidth().padding(vertical = dimensionResource(R.dimen.cardPadding)),
        ) {
            items(mockData) { item ->
                ListItemUI(data = item, onClick = onNoteClick)
            }
        }

        Spacer(Modifier.height(24.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                stringResource(R.string.main_recently_new_note_title),
                style = MaterialTheme.typography.titleMedium
            )
            Row(
                modifier = Modifier.fillMaxWidth(fraction = 0.25f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly

            ) {
                Text(
                    stringResource(R.string.more_content),
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.Gray
                )
                Icon(
                    painter = painterResource(R.drawable.ic_right_arrow),
                    contentDescription = stringResource(R.string.icon_more_content),
                    modifier = Modifier.size(14.dp),
                    tint = Color.Gray,

                )
            }
        }

        LazyRow(
            modifier = Modifier.fillMaxWidth().padding(vertical = dimensionResource(R.dimen.cardPadding)),
        ) {
            items(mockData) { item ->
                ListItemUI(data = item, onClick = onNoteClick)
            }
        }

    }

}

@Composable
fun ListItemUI(data: NoteWithUser, onClick: (NoteWithUser) -> Unit) {
    Card(
        modifier = Modifier
            .width(dimensionResource(R.dimen.card_width))
            .height(dimensionResource(R.dimen.card_height))
            .padding(dimensionResource(R.dimen.cardPadding)),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp,
            pressedElevation = 8.dp
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

@Preview(showBackground = true)
@Composable
private fun HomeTabScreenPreview() {
    val previewNavController = rememberNavController()
    HomeTabScreen(navController = previewNavController)
}