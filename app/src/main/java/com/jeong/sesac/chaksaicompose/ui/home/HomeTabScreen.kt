package com.jeong.sesac.chaksaicompose.ui.home

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jeong.sesac.chaksaicompose.R
import com.jeong.sesac.chaksaicompose.common.mockNotes
import com.jeong.sesac.chaksaicompose.component.CommonSpacer
import com.jeong.sesac.chaksaicompose.component.home.LazyColUI
import com.jeong.sesac.chaksaicompose.component.home.TitleUi
import com.jeong.sesac.chaksaicompose.nav_graph.ScreenRoutes
import com.jeong.sesac.chaksaicompose.ui.theme.AppTheme
import com.jeong.sesac.feature.model.NoteWithUser


@Composable
fun HomeTabScreen(
    navController: NavController
) {
    HomeTabContent(navController)
}


@Composable
private fun HomeTabContent(navController : NavController) {
    val mockData = mockNotes
    // post 디테일 페이지 이동 nav 함수
    val onPostDetailClick = { noteData: NoteWithUser ->
        navController.navigate(ScreenRoutes.PostDetailScreenGroup.LibraryPostDetail.routeName + "/${noteData.id}")
    }
    // 이번 주 인기 post 리스트 페이지 이동 함수
    val onWeeklyPostsClick = {
        navController.navigate(ScreenRoutes.HomeTabScreenGroup.WeeklyPosts)
    }
    // 이번 주 새로 등록된 post 리스트 페이지 이동 함수
    val onNewPostsClick = {
        navController.navigate(ScreenRoutes.HomeTabScreenGroup.NewPosts)
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CommonSpacer(24)

        Text(
            stringResource(R.string.main_header, "하겐다즈"),
            style = AppTheme.typography.titleLarge
        )
        CommonSpacer(24)

        TitleUi(R.string.main_weekly_popular_note_title, onMoreClick = onWeeklyPostsClick)

        LazyColUI(mockData, onPostClick = onPostDetailClick)

        CommonSpacer(24)

        TitleUi(R.string.main_recently_new_note_title, onMoreClick = onNewPostsClick)

        LazyColUI(mockData, onPostClick = onPostDetailClick)

    }

}


@Preview(showBackground = true)
@Composable
private fun HomeTabScreenPreview() {
    val previewNavController = rememberNavController()
    AppTheme{
    HomeTabScreen(navController = previewNavController)
    }
}