package com.jeong.sesac.chaksaicompose.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jeong.sesac.chaksaicompose.R
import com.jeong.sesac.chaksaicompose.common.mockNotes
import com.jeong.sesac.chaksaicompose.component.CommonTopAppBar
import com.jeong.sesac.feature.model.NoteWithUser
import kotlinx.coroutines.launch

private fun tabItemList() = listOf("최신순", "좋아요높은순", "좋아요낮은순")


@Composable
fun NewNotesScreen (navController : NavController
) {
    CommonTopAppBar("이번주 인기쪽지", "go to back") { paddingValue ->
        NotesContent(tabItemList(), paddingValue)
    }
}



@Composable
fun NotesContent(tabItemList: List<String>, innerPadding: PaddingValues) {
    val tabTag = tabItemList
    val tabPageState = rememberPagerState(initialPage = 0) {
        tabTag.size
    }
    Column(
        modifier = Modifier.fillMaxSize().padding(innerPadding)
    ) {
        TabLayout(tabTag, tabPageState)
        TabContent(tabPageState)
    }
}

@Composable
fun ListItem(data: NoteWithUser) {
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

@Composable
fun GridNoteList(
    notes: List<NoteWithUser>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(notes) {note ->
            ListItem(note)

        }
    }
}

@Composable
fun TabContent(
    pagerState: PagerState
) {
    HorizontalPager(state = pagerState) { idx ->
        when(idx) {
            0 -> GridNoteList(mockNotes)
            1 -> GridNoteList(mockNotes)
            2 -> GridNoteList(mockNotes)
        }
    }
}

@Composable
fun TabLayout(tabItem : List<String>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        indicator = {tabPositions ->
            TabRowDefaults.SecondaryIndicator(
                modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                color = MaterialTheme.colorScheme.primary,
                height = 3.dp
            )
        },
        modifier = Modifier.fillMaxWidth().wrapContentHeight()
    ) {
        tabItem.forEachIndexed { idx, data ->
            Tab(
                selectedContentColor = MaterialTheme.colorScheme.primary,
                unselectedContentColor = Color.Gray,
                selected = pagerState.currentPage == idx,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(idx)
                    }
                },
                text = { Text(text = tabItem.first(), fontSize = 12.sp) }
            )

        }


    }
}



@Preview(showBackground = true)
@Composable
private fun NewNotesScreenPreview() {
    val previewNavController = rememberNavController()
    NewNotesScreen(navController = previewNavController)
}