package com.jeong.sesac.chaksaicompose.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jeong.sesac.chaksaicompose.R
import com.jeong.sesac.chaksaicompose.common.AppPreferenceManager
import com.jeong.sesac.chaksaicompose.component.CommonSpacer
import com.jeong.sesac.chaksaicompose.component.home.LazyColUI
import com.jeong.sesac.chaksaicompose.component.home.TitleUi
import com.jeong.sesac.chaksaicompose.model.UiState
import com.jeong.sesac.chaksaicompose.ui.theme.AppTheme
import com.jeong.sesac.chaksaicompose.viewmodel.PostListViewModel
import com.jeong.sesac.chaksaicompose.viewmodel.viewmodel_factory.appViewModelFactory
import com.jeong.sesac.feature.model.PostWithUser


@Composable
fun HomeTabScreen(
    preference: AppPreferenceManager,
    onNavigationUp: () -> Unit,
    onNavigationToNewPostList: (posts: List<PostWithUser>) -> Unit,
    onNavigationToPopularNotes: (posts: List<PostWithUser>) -> Unit,
    onNavigationToDetailPost: (postId: String) -> Unit,
) {
    HomeTabContent(
        preference,
        onNavigationUp,
        onNavigationToNewPostList,
        onNavigationToPopularNotes,
        onNavigationToDetailPost,
    )
}


@Composable
private fun HomeTabContent(
    preference: AppPreferenceManager,
    onNavigationUp: () -> Unit,
    onNavigationToNewPostList: (postData: List<PostWithUser>) -> Unit,
    onNavigationToPopularNotes: (postData: List<PostWithUser>) -> Unit,
    onNavigationToDetailPost: (postId: String) -> Unit,
    viewModel: PostListViewModel = viewModel(factory = appViewModelFactory)
) {
    val nickname = preference.nickName
    val userId = preference.userId

    LaunchedEffect(Unit) {
        viewModel.getNewPostList(userId)
        viewModel.getPopularNoteList(userId)
    }

    val popularPostsState by viewModel.popularPostsState.collectAsStateWithLifecycle()
    val newPostsState by viewModel.newPostsState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CommonSpacer(24)

        Text(
            stringResource(R.string.main_header, nickname),
            style = AppTheme.typography.titleLarge
        )
        CommonSpacer(24)

        TitleUi(R.string.main_weekly_popular_note_title, onMoreClick = {
            (popularPostsState as UiState.Success).data.let { posts ->
                onNavigationToPopularNotes(posts)
            }
        })
        when (val popularPosts = popularPostsState) {
            is UiState.Loading -> {
            }

            is UiState.Success -> {
                LazyColUI(popularPosts.data, onPostClick = { onNavigationToDetailPost(it.id) })

            }

            is UiState.Error -> {
                Text("err : ${popularPosts.error}")
            }
        }

        CommonSpacer(24)

        TitleUi(R.string.main_recently_new_note_title, onMoreClick = {
            (newPostsState as UiState.Success).data.let { newPosts -> onNavigationToNewPostList(newPosts) }
        })

        when (val newPosts = newPostsState) {
            is UiState.Loading -> {
            }

            is UiState.Success -> {
                LazyColUI(newPosts.data, onPostClick = { onNavigationToDetailPost(it.id) })

            }

            is UiState.Error -> {
                Text("err : ${newPosts.error}")
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
private fun HomeTabScreenPreview() {
    AppTheme {
//        HomeTabScreen()
    }
}