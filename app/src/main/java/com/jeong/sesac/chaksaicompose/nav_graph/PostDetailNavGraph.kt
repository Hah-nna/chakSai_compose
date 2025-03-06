package com.jeong.sesac.chaksaicompose.nav_graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.jeong.sesac.chaksaicompose.R
import com.jeong.sesac.chaksaicompose.ui.postDetail.PostDetailScreen
import com.jeong.sesac.feature.model.NoteWithUser
import com.jeong.sesac.feature.model.UserInfo


fun NavGraphBuilder.PostDetailNavGraph(navController: NavController) {
    navigation(
        startDestination = ScreenRoutes.PostDetailScreenGroup.LibraryPostDetail.routeName,
        route = "post_detail_nav_graph"
    ) {
        composable(
            route = ScreenRoutes.PostDetailScreenGroup.LibraryPostDetail.routeName + "/{postId}",
            arguments = listOf(
                navArgument("postId") {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) { backStackEntry ->
            val postId = backStackEntry.arguments?.getString("postId")
                ?: return@composable

            PostDetailScreen(
                noteId = postId,
                note = tempNoteDate(),
                onBackClick = { navController.popBackStack() }
            )
        }
        composable(
            route = ScreenRoutes.PostDetailScreenGroup.EditPostScreen.routeName + "/{postId}",
            arguments = listOf(
                navArgument("postId") {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) {
            backStackEntry ->
            val postId = backStackEntry.arguments?.getString("postId")
                ?: return@composable

//            EditPostScreen()
        }
        composable(
            route = ScreenRoutes.PostDetailScreenGroup.EditCommentScreen.routeName + "/{commentId}",
            arguments = listOf(
                navArgument("commentId") {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) { backStackEntry ->
            val commentId = backStackEntry.arguments?.getString("commentId")
                ?: return@composable
//            EditCommentScreen
        }
    }



}
private fun tempNoteDate(): NoteWithUser {
    return NoteWithUser(
        id = "note3",
        userInfo = UserInfo(
            id = "user456",
            profile = "https://example.com/profile.jpg",
            nickName = "벤앤제리"
        ),
        image = R.drawable.ic_launcher_background.toString(),
        title = "세 번째 쪽지",
        createdAt = System.currentTimeMillis(),
        libraryName = "구의도서관",
        content = "새로 나온 책 추천합니다",
        likes = emptyList()
    )
}