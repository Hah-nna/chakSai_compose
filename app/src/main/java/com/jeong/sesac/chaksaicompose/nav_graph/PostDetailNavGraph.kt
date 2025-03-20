package com.jeong.sesac.chaksaicompose.nav_graph

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.jeong.sesac.chaksaicompose.common.AppPreferenceManager
import com.jeong.sesac.chaksaicompose.ui.post_detail.EditCommentScreen
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
    val commentId: String,
    val postId: String,
    val userId: String,
    val content: String
)

/**
 * 포스트 디테일 그래프
 * 포스트 수정, 코멘트 수정 스크린도 포함
 * */
fun NavGraphBuilder.postDetailNavGraph(
    preference: AppPreferenceManager,
    onNavigationUp: () -> Unit,
    onNavigationToEditPost: (postId: String) -> Unit,
    onNavigationToEditComment: (commentId: String, postId: String, userId: String, content: String) -> Unit,
) {
    navigation<PostDetailRoute>(
        startDestination = PostDetail::class
//        route = "post_detail_nav_graph"
    ) {
        composable<PostDetail> { backStackEntry ->
            val postId = backStackEntry.arguments?.getString("postId")
                ?: return@composable

            PostDetailScreen(
                preference = preference,
                postId = postId,
                onBackClick = onNavigationUp,
                onEditPostClick = { onNavigationToEditPost(postId) },
                onEditCommentClick = { commentId, content -> onNavigationToEditComment(commentId, postId, preference.userId, content) }
            )
        }
        composable<PostEdit> { backStackEntry ->
            val postId = backStackEntry.arguments?.getString("postId")
                ?: return@composable

//            EditPostScreen(
//                postId = postId,
//                onBackClick = onNavigationUp,
//                onEditComplete =
//            )
        }

        composable<CommentEdit>
        { backStackEntry ->
            val args = backStackEntry.toRoute<CommentEdit>()
            EditCommentScreen(
                commentId = args.commentId,
                postId = args.postId,
                userId = args.userId,
                content = args.content,
                onBackClick = onNavigationUp,
            )
        }
    }
}

fun NavController.navigateToEditComment(
    commentId: String,
    postId: String,
    userId: String,
    content: String
) {
    navigate(
        CommentEdit(
            commentId = commentId,
            postId = postId,
            userId = userId,
            content = content
        )
    )
}