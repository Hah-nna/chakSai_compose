package com.jeong.sesac.chaksaicompose.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jeong.sesac.chaksaicompose.common.mockNotes
import com.jeong.sesac.chaksaicompose.component.CommonTopAppBar
import com.jeong.sesac.chaksaicompose.component.tab.GridNoteList
import com.jeong.sesac.chaksaicompose.component.tab.TabContentUI
import com.jeong.sesac.chaksaicompose.component.tab.TabLayoutUI
import com.jeong.sesac.chaksaicompose.ui.theme.AppTheme

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
    val pagerState = rememberPagerState(initialPage = 0) { tabItemList.size }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        TabLayoutUI(
            tabItem = tabItemList,
            pagerState = pagerState,
            modifier = Modifier.fillMaxWidth().wrapContentHeight(),
            onTabSelected = {}
        )

        TabContentUI(
            pagerState = pagerState,
            pages = listOf(
                { GridNoteList(mockNotes) },
                { GridNoteList(mockNotes) },
                { GridNoteList(mockNotes) }
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NewNotesScreenPreview() {
    val previewNavController = rememberNavController()
    AppTheme {
    NewNotesScreen(navController = previewNavController)
    }
}