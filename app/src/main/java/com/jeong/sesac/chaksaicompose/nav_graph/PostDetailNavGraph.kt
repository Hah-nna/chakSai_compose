package com.jeong.sesac.chaksaicompose.nav_graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.jeong.sesac.chaksaicompose.ui.post_detail.PostDetailScreen
import kotlinx.serialization.Serializable

@Serializable
data object PostDetailRoute

@Serializable
data class PostDetail(
    val postId: String
)

@Serializable
data class PostEdit(
    val postId: String
)

@Serializable
data class CommentEdit(
    val commentId: String
)

/**
 * 포스트 디테일 그래프
 * 포스트 수정, 코멘트 수정 스크린도 포함
 * */
fun NavGraphBuilder.postDetailNavGraph(
    onNavigationUp: () -> Unit,
    onNavigationToEditPost: (postId: String) -> Unit,
    onNavigationToEditComment: (commentId: String) -> Unit
) {
    navigation<PostDetailRoute>(
        startDestination = PostDetail::class
//        route = "post_detail_nav_graph"
    ) {
        composable<PostDetail> { backStackEntry ->
            val postId = backStackEntry.arguments?.getString("postId")
                ?: return@composable

            PostDetailScreen(
                postId = postId,
                onBackClick = onNavigationUp,
                onEditPostClick = { onNavigationToEditPost(postId) },
                onEditCommentClick = { commentId -> onNavigationToEditComment(commentId) }
            )
        }
        composable<PostEdit> { backStackEntry ->
            val postId = backStackEntry.arguments?.getString("postId")
                ?: return@composable

//            EditPostScreen(
//                postId = postId,
//                onBackClick = onNavigationUp,
//            )
        }

        composable<CommentEdit>
//        (
//            route = ScreenRoutes.PostDetailScreenGroup.EditCommentScreen.routeName + "/{commentId}",
//            arguments = listOf(
//                navArgument("commentId") {
//                    type = NavType.StringType
//                    nullable = false
//                }
//            )
//        )
        { backStackEntry ->
            val commentId = backStackEntry.arguments?.getString("commentId")
                ?: return@composable
//            EditCommentScreen(
//                commentId = commentId,
//                onBackClick = onNavigationUp,
//            )
        }
    }
}