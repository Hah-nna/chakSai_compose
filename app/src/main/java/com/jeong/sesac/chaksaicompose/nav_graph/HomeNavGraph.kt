package com.jeong.sesac.chaksaicompose.nav_graph

import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.jeong.sesac.chaksaicompose.common.AppPreferenceManager
import com.jeong.sesac.chaksaicompose.ui.home.HomeTabScreen
import com.jeong.sesac.chaksaicompose.ui.home.NewPostsScreen
import com.jeong.sesac.chaksaicompose.ui.home.PopularPostsScreen
import com.jeong.sesac.feature.model.PostWithUser
import kotlinx.serialization.Serializable

@Serializable
data object Home

@Serializable
data object NewPosts

@Serializable
data object PopularPosts

@Serializable
data object HomeBaseRoute

fun NavGraphBuilder.homeNavGraph (
    preference: AppPreferenceManager,
    onNavigationUp: () -> Unit,
    onNavigationToNewPostList: (posts: List<PostWithUser>) -> Unit,
    onNavigationToPopularNotes: (posts: List<PostWithUser>) -> Unit,
    onNavigationToDetailPost: (postId: String) -> Unit
) {
        navigation<HomeBaseRoute>(
            startDestination = Home,
        ) {

            /**
             * 홈 스크린
             * */
            composable<Home> {
                HomeTabScreen(preference, onNavigationUp, onNavigationToNewPostList, onNavigationToPopularNotes, onNavigationToDetailPost)
            }

            /**
             * 새로운 포스트 리스트 스크린
             * */
            composable<NewPosts> {
                NewPostsScreen(preference, onNavigationUp, onNavigationToDetailPost)
            }
            /**
             * 최근 인기 포스트 리스트 스크린
             * */
            composable<PopularPosts> {
                PopularPostsScreen(preference, onNavigationUp, onNavigationToDetailPost)
            }
        }
}

