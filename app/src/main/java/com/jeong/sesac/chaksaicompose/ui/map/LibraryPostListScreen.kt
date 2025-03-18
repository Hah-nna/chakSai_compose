package com.jeong.sesac.chaksaicompose.ui.map

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.jeong.sesac.chaksaicompose.R
import com.jeong.sesac.chaksaicompose.common.mockNotes
import com.jeong.sesac.chaksaicompose.component.CommonSpacer
import com.jeong.sesac.chaksaicompose.component.img.ProfileSmall
import com.jeong.sesac.chaksaicompose.ui.theme.AppTheme
import com.jeong.sesac.feature.model.PostWithUser

@Composable
fun LibraryPostListScreen(libraryName: String, onBackClick: () -> Unit) {
    LibraryPostListContainer(libraryName, onBackClick)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryPostListContainer(libraryName: String, onBackClick: () -> Unit) {
    var isMenuExpanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                ),
                title = { Text(libraryName) },
                navigationIcon = {
                    TextButton(
                        onClick = { isMenuExpanded = !isMenuExpanded },
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("평화나루 도서관", style = AppTheme.typography.labelMedium)
                        }
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Filled.Search, contentDescription = stringResource(R.string.iconButton))
                    }
                },
            )
        }
    ) { padding ->
        LibraryPostListContent(padding)
    }
}

@Composable
fun LibraryPostListContent(padding: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        LazyColumn {
            items(mockNotes) { item ->
                PostItemUI(itemData = item)
            }
        }
    }
}

@Composable
fun PostItemUI(itemData: PostWithUser) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = AppTheme.size.small),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            modifier = Modifier
                .width(dimensionResource(R.dimen.cardXl))
                .height(dimensionResource(R.dimen.cardXl))
                .padding(AppTheme.size.small),
            elevation = CardDefaults.cardElevation(4.dp),
        ) {
        }

        Column(
            modifier = Modifier
                .padding(AppTheme.size.small)
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,

                ) {
                ProfileSmall(itemData.userInfo.profile)
                Text(
                    itemData.userInfo.nickName,
                    overflow = TextOverflow.Ellipsis,
                    style = AppTheme.typography.bodySmall,
                    modifier = Modifier.padding(horizontal = AppTheme.size.small)
                )
                Spacer(Modifier.weight(1f))
                Text("5분전", style = AppTheme.typography.bodySmall)
            }

            CommonSpacer(12)

            Text(itemData.title, overflow = TextOverflow.Ellipsis, style = AppTheme.typography.titleSmall)

            CommonSpacer(12)

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(itemData.libraryName, style = AppTheme.typography.bodySmall, color = Color.DarkGray)
                Icon(imageVector = Icons.Filled.FavoriteBorder, tint = Color.White, contentDescription = "like", modifier = Modifier.size(24.dp))
            }
        }
    }
    HorizontalDivider(modifier = Modifier.fillMaxWidth(), thickness = 0.5.dp)
}


@Preview(showBackground = true)
@Composable
private fun LibraryPostListScreenPreview() {
    val previewNavController = rememberNavController()
    AppTheme {
        LibraryPostListScreen("청량리도서관", {} )
    }
}
