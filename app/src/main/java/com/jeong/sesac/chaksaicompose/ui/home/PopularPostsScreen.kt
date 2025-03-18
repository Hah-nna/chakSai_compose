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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jeong.sesac.chaksaicompose.R
import com.jeong.sesac.chaksaicompose.common.AppPreferenceManager
import com.jeong.sesac.chaksaicompose.common.mockNotes
import com.jeong.sesac.chaksaicompose.component.BasicTopAppBar
import com.jeong.sesac.chaksaicompose.component.tab.GridNoteList
import com.jeong.sesac.chaksaicompose.component.tab.TabContentUI
import com.jeong.sesac.chaksaicompose.component.tab.TabLayoutUI
import com.jeong.sesac.chaksaicompose.ui.theme.AppTheme

private fun tabItemList() = listOf("최신순", "좋아요높은순", "좋아요낮은순")

@Composable
fun PopularPostsScreen (
    preference: AppPreferenceManager,
    onNavigationUp: () -> Unit,
    onNavigationToDetailPost: (postId: String) -> Unit
) {
    BasicTopAppBar(stringResource(R.string.main_weekly_popular_note_title), stringResource(R.string.goToBack), onNavigationUp) { padding ->
        PopularPostsContent(tabItemList(), padding, onNavigationToDetailPost)
    }
}

@Composable
fun PopularPostsContent(tabItemList: List<String>, innerPadding: PaddingValues, onNavigationToDetailPost: (postId: String) -> Unit) {
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
                { GridNoteList(mockNotes, onNavigationToDetailPost) },
                { GridNoteList(mockNotes, onNavigationToDetailPost) },
                { GridNoteList(mockNotes, onNavigationToDetailPost) }
            )
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun PopularNotesScreenPreview() {
    AppTheme {
//        PopularPostsScreen()
    }
}