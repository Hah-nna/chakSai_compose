package com.jeong.sesac.chaksaicompose.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jeong.sesac.chaksaicompose.nav_graph.BottomNavigationItem
import com.jeong.sesac.chaksaicompose.nav_graph.ScreenRoutes
import com.jeong.sesac.chaksaicompose.nav_graph.commonNoteDetailNavGraph
import com.jeong.sesac.chaksaicompose.ui.chatting.ChattingTabScreen
import com.jeong.sesac.chaksaicompose.ui.home.HomeTabScreen
import com.jeong.sesac.chaksaicompose.ui.home.NewNotesScreen
import com.jeong.sesac.chaksaicompose.ui.home.WeeklyNotesScreen
import com.jeong.sesac.chaksaicompose.ui.map.LibraryMapTabScreen
import com.jeong.sesac.chaksaicompose.ui.map.LibraryWriteNoteScreen
import com.jeong.sesac.chaksaicompose.ui.map.MapSearchScreen
import com.jeong.sesac.chaksaicompose.ui.myPage.MyPageTabScreen
import com.jeong.sesac.chaksaicompose.ui.myPage.MyProfileScreen
import com.jeong.sesac.chaksaicompose.ui.record.RecordTabScreen

@Preview(showBackground = true)
@Composable
fun MainEntryScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            // bottom nav bar
            NavigationBar {
                BottomNavigationItem().renderBottomNavigationItems()
                    .forEachIndexed { _, navItem ->
                        NavigationBarItem(
                            selected = navItem.route == currentDestination?.route,
                            label = {
                                Text(
                                    text = navItem.tabName,
                                    color = Color.Gray
                                )
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(id = navItem.icon),
                                    contentDescription = navItem.tabName,
                                    modifier = Modifier.size(24.dp),
                                    tint = Color.Gray
                                )
                            },
                            onClick = {
                                navController.navigate(navItem.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )

                    }
            }
        }
    ) {
        paddingValues ->
        NavHost(
            navController = navController,
            startDestination = ScreenRoutes.HomeTab.routeName,
            modifier = Modifier.padding(paddingValues = paddingValues)
        ) {

            // home tab (main)
            composable(ScreenRoutes.HomeTab.routeName) {
                HomeTabScreen(navController)
            }
            // NewNotes (새로 등록된 쪽지 리스트 스크린) (main)
            composable(ScreenRoutes.HomeTabScreenGroup.NewNotes.routeName) {
                NewNotesScreen(navController)
            }
            // 이 주의 인기쪽지
            composable(ScreenRoutes.HomeTabScreenGroup.WeeklyNotes.routeName) {
                WeeklyNotesScreen(navController)
            }

            // libraryMap tab
            composable(ScreenRoutes.LibraryMapTab.routeName) {
                LibraryWriteNoteScreen(navController)
            }

            // 쪽지작성 스크린
            composable(ScreenRoutes.LibraryMapTabScreenGroup.LibraryWriteNote.routeName) {
                LibraryMapTabScreen(navController)
            }

            // Map Search
            composable(ScreenRoutes.LibraryMapTabScreenGroup.MapSearch.routeName) {
                MapSearchScreen(navController)
            }

            // record tab RecordTabScreen
            composable(ScreenRoutes.RecordTab.routeName) {
                RecordTabScreen(navController)
            }

            // chatting tab
            composable(ScreenRoutes.ChattingTab.routeName) {
                ChattingTabScreen(navController)
            }

            // MyPageTab
            composable(ScreenRoutes.MyPageTab.routeName) {
                MyPageTabScreen(navController)
            }

            // myProfile
            composable(ScreenRoutes.MyPageScreenGroup.MyProfile.routeName) {
                MyProfileScreen(navController)
            }

            commonNoteDetailNavGraph(navController)

        }
    }

}