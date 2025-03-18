package com.jeong.sesac.chaksaicompose.ui.record

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jeong.sesac.chaksaicompose.common.mockNotes
import com.jeong.sesac.chaksaicompose.component.tab.GridNoteList
import com.jeong.sesac.chaksaicompose.component.tab.TabContentUI
import com.jeong.sesac.chaksaicompose.component.tab.TabLayoutUI
import com.jeong.sesac.chaksaicompose.ui.theme.AppTheme

private fun tabItemList() = listOf("내쪽지", "좋아요한쪽지")

@Composable
fun RecordTabScreen(navController: NavController) {
    RecordContent()
}

@Composable
fun RecordContent() {
    val tabTag = tabItemList()
    val tabPagerState = rememberPagerState(initialPage = 0) {
        tabTag.size
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TabLayoutUI(
            tabItemList(),
            tabPagerState,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            onTabSelected = {})
        TabContentUI(
            tabPagerState,
            listOf(
                { GridNoteList(mockNotes, {}) },
                { GridNoteList(mockNotes, {}) },
            )
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun RecordTabScreenPreview() {
    val previewNavController = rememberNavController()
    AppTheme {
        RecordTabScreen(navController = previewNavController)
    }
}