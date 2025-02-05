package com.jeong.sesac.chaksaicompose.ui.record

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jeong.sesac.chaksaicompose.ui.home.TabContent
import com.jeong.sesac.chaksaicompose.ui.home.TabLayout

private fun tabItemList() = listOf("내쪽지", "좋아요한쪽지")
@Composable
fun RecordTabScreen (navController : NavController){
    RecordContent()
}

@Composable
fun RecordContent() {
    val tabTag = tabItemList()
    val tabPagerState = rememberPagerState (initialPage = 0) {
        tabTag.size
    }

    Column (
        modifier = Modifier.fillMaxSize()
    ) {
        TabLayout(tabItemList(), tabPagerState)
        TabContent(tabPagerState)
    }

}

@Preview(showBackground = true)
@Composable
private fun RecordTabScreenPreview() {
    val previewNavController = rememberNavController()
    RecordTabScreen(navController = previewNavController)
}