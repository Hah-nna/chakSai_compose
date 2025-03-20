package com.jeong.sesac.chaksaicompose.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jeong.sesac.chaksaicompose.R
import com.jeong.sesac.chaksaicompose.common.AppPreferenceManager
import com.jeong.sesac.chaksaicompose.component.BasicTopAppBar
import com.jeong.sesac.chaksaicompose.component.tab.GridNoteList
import com.jeong.sesac.chaksaicompose.component.tab.TabContentUI
import com.jeong.sesac.chaksaicompose.component.tab.TabLayoutUI
import com.jeong.sesac.chaksaicompose.model.UiState
import com.jeong.sesac.chaksaicompose.ui.theme.AppTheme
import com.jeong.sesac.chaksaicompose.viewmodel.PostListViewModel
import com.jeong.sesac.chaksaicompose.viewmodel.viewmodel_factory.appViewModelFactory
import com.jeong.sesac.feature.model.PostWithUser

private fun tabItemList() = listOf("최신순", "좋아요높은순", "좋아요낮은순")

@Composable
fun PopularPostsScreen(
    preference: AppPreferenceManager,
    onNavigationUp: () -> Unit,
    onNavigationToDetailPost: (postId: String) -> Unit
) {

    val viewModel: PostListViewModel = viewModel(factory = appViewModelFactory)
    val popularPostList = viewModel.popularPostsState.collectAsState()

    LaunchedEffect(Unit) {
        if (popularPostList.value !is UiState.Success) {
            viewModel.getPopularNoteList(preference.userId)
        }
    }

    BasicTopAppBar(
        stringResource(R.string.main_weekly_popular_note_title),
        stringResource(R.string.goToBack),
        onNavigationUp
    ) { padding ->
        when (val state = popularPostList.value) {
            is UiState.Loading -> {}
            is UiState.Success -> PopularPostsContent(
                tabItemList(),
                padding,
                state.data,
                onNavigationToDetailPost
            )

            is UiState.Error -> {}
        }
    }
}

@Composable
fun PopularPostsContent(
    tabItemList: List<String>,
    innerPadding: PaddingValues,
    data: List<PostWithUser>,
    onNavigationToDetailPost: (postId: String) -> Unit
) {
    val pagerState = rememberPagerState(initialPage = 0) { tabItemList.size }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        TabLayoutUI(
            tabItem = tabItemList,
            pagerState = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            onTabSelected = {}
        )

        TabContentUI(
            pagerState = pagerState,
            pages = listOf(
                { GridNoteList(data.sortedBy { it.createdAt }, onNavigationToDetailPost) },
                { GridNoteList(data.sortedBy { it.likes.size }, onNavigationToDetailPost) },
                {
                    GridNoteList(
                        data.sortedByDescending { it.likes.size },
                        onNavigationToDetailPost
                    )
                }
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