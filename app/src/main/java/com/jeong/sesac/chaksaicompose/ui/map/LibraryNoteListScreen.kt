package com.jeong.sesac.chaksaicompose.ui.map

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jeong.sesac.chaksaicompose.R
import com.jeong.sesac.chaksaicompose.common.mockNotes
import com.jeong.sesac.chaksaicompose.ui.home.NewNotesScreen
import com.jeong.sesac.chaksaicompose.ui.theme.AppTypography
import com.jeong.sesac.feature.model.NoteWithUser

@Composable
fun LibraryNoteListScreen(navController: NavController) {
    LibraryNoteListContainer()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryNoteListContainer() {
    var isMenuExpanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                ),
                title = { Text("") },
                navigationIcon = {
                    TextButton(
                        onClick = { isMenuExpanded = !isMenuExpanded },
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("평화나루 도서관", style = AppTypography.labelMedium)
                            Icon(
                                Icons.Filled.KeyboardArrowDown,
                                contentDescription = "library_filter",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                    LibraryDropdownMenu(
                        expanded = isMenuExpanded,
                        onDismiss = { isMenuExpanded = false })
                },
                actions = {
                    // RowScope here, so these icons will be placed horizontally
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(Icons.Filled.Search, contentDescription = "Localized description")
                    }

                },
            )
        }
    ) { padding ->
        LibraryNoteListContent(padding)

    }

}

@Composable
fun LibraryDropdownMenu(expanded: Boolean, onDismiss: () -> Unit) {
    DropdownMenu(expanded = expanded, onDismissRequest = onDismiss) {
        DropdownMenuItem(
            text = { Text("평화나루도서관") },
            onClick = onDismiss,
        )
        DropdownMenuItem(
            text = { Text("다른 도서관") },
            onClick = onDismiss,
        )
    }
}

@Composable
fun LibraryNoteListContent(padding: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        LazyColumn {
            items(mockNotes) { item ->
                NoteItemUI(itemData = item)
            }
        }
    }

}

@Composable
fun NoteItemUI(itemData: NoteWithUser) {

    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            modifier = Modifier
                .width(140.dp)
                .height(140.dp)
                .padding(8.dp),
            elevation = CardDefaults.cardElevation(4.dp),
        ) {
        }

        Column(
            modifier = Modifier.padding(8.dp).fillMaxWidth().wrapContentHeight(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,

                ) {
                Image(
                    painter = painterResource(R.drawable.ic_launcher_background),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = null,
                    modifier = Modifier
                        .width(24.dp)
                        .height(24.dp)
                        .clip(CircleShape)
                )
                Text(
                    itemData.userInfo.nickName,
                    overflow = TextOverflow.Ellipsis,
                    style = AppTypography.bodyMedium,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Spacer(Modifier.weight(1f))
                Text("5분전", style = AppTypography.bodySmall)
            }

            Spacer(Modifier.height(12.dp))

            Text(itemData.title, overflow = TextOverflow.Ellipsis, style = AppTypography.titleSmall)

            Spacer(Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(itemData.libraryName, style = AppTypography.bodyMedium, color = Color.DarkGray)
                Icon(imageVector = Icons.Filled.FavoriteBorder, tint = Color.White, contentDescription = "like", modifier = Modifier.size(24.dp))
            }
        }
    }

    HorizontalDivider(modifier = Modifier.fillMaxWidth(), thickness = 0.5.dp)

}


@Preview(showBackground = true)
@Composable
private fun LibraryNoteListScreenPreview() {
    val previewNavController = rememberNavController()
    LibraryNoteListScreen(navController = previewNavController)
}
