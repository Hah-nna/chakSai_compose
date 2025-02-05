package com.jeong.sesac.chaksaicompose.nav_graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.jeong.sesac.chaksaicompose.R
import com.jeong.sesac.chaksaicompose.ui.noteDetail.NoteDetailScreen
import com.jeong.sesac.feature.model.NoteWithUser
import com.jeong.sesac.feature.model.UserInfo
import java.util.Date


fun NavGraphBuilder.commonNoteDetailNavGraph(navController: NavController) {
    navigation(
        startDestination = ScreenRoutes.CommonScreenGroup.LibraryNoteDetail.routeName,
        route = "common_note_detail_nav_graph"
    ) {
        composable(
            route = ScreenRoutes.CommonScreenGroup.LibraryNoteDetail.routeName + "/{noteId}",
            arguments = listOf(
                navArgument("noteId") {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString("noteId")
                ?: return@composable

            NoteDetailScreen(
                noteId = noteId,
                note = tempNoteDate(),
                onBackClick = { navController.popBackStack() }
            )
        }
    }

}
private fun tempNoteDate(): NoteWithUser {
    return NoteWithUser(
        id = "note3",
        userInfo = UserInfo(
            id = "user456",
            profile = "https://example.com/profile2.jpg",
            nickName = "벤앤제리"
        ),
        image = R.drawable.ic_launcher_background,
        title = "세 번째 쪽지",
        createdAt = Date(System.currentTimeMillis() - 48 * 60 * 60 * 1000),  // 2일 전
        libraryName = "구의도서관",
        content = "새로 나온 책 추천합니다",
        likes = 15
    )
}