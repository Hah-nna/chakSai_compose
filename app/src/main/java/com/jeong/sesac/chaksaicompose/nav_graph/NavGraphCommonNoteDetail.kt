package com.jeong.sesac.chaksaicompose.nav_graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.jeong.sesac.chaksaicompose.ui.noteDetail.NoteDetailScreen


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
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}