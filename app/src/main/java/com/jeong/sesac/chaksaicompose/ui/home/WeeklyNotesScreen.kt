package com.jeong.sesac.chaksaicompose.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jeong.sesac.chaksaicompose.component.CommonTopAppBar

private fun tabItemList() = listOf("최신순", "좋아요높은순", "좋아요낮은순")

@Composable
fun WeeklyNotesScreen (
    navController : NavController
) {
    CommonTopAppBar("최근 등록된 쪽지", "go to back") { padding ->
        NotesContent(tabItemList(), padding)
    }
}

@Preview(showBackground = true)
@Composable
private fun WeeklyNotesScreenPreview() {
    val previewNavController = rememberNavController()
    WeeklyNotesScreen(navController = previewNavController)
}