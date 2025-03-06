package com.jeong.sesac.chaksaicompose.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jeong.sesac.chaksaicompose.R
import com.jeong.sesac.chaksaicompose.nav_graph.BottomNavigationItem
import com.jeong.sesac.chaksaicompose.nav_graph.PostDetailNavGraph
import com.jeong.sesac.chaksaicompose.nav_graph.ScreenRoutes
import com.jeong.sesac.chaksaicompose.ui.home.HomeTabScreen
import com.jeong.sesac.chaksaicompose.ui.home.NewPostsScreen
import com.jeong.sesac.chaksaicompose.ui.home.WeeklyPostsScreen
import com.jeong.sesac.chaksaicompose.ui.map.LibraryMapTabScreen
import com.jeong.sesac.chaksaicompose.ui.map.LibraryPostListScreen
import com.jeong.sesac.chaksaicompose.ui.map.LibraryWritePostScreen
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
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Thin,

                                )
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(id = navItem.icon),
                                    contentDescription = navItem.tabName,
                                    modifier = Modifier.size(20.dp),

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
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color.Black,
                                selectedTextColor = Color.Black,
                                indicatorColor = Color.Transparent,
                                unselectedIconColor = Color.Gray,
                                unselectedTextColor = Color.Gray
                            )

                        )

                    }
            }
        }
    ) {
        paddingValues ->
        NavHost(
            navController = navController,
            startDestination = ScreenRoutes.HomeTab.routeName,
            modifier = Modifier.padding(
                start = dimensionResource(R.dimen.basicPadding),
                end = dimensionResource(R.dimen.basicPadding),
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding()
            )
        ) {

            // home tab (main)
            composable(ScreenRoutes.HomeTab.routeName) {
                HomeTabScreen(navController)
            }
            // NewNotes (새로 등록된 쪽지 리스트 스크린) (main)
            composable(ScreenRoutes.HomeTabScreenGroup.NewNotes.routeName) {
                NewPostsScreen(navController)
            }
            // 이 주의 인기쪽지
            composable(ScreenRoutes.HomeTabScreenGroup.WeeklyNotes.routeName) {
                WeeklyPostsScreen(navController)
            }

            // libraryMap tab
            composable(ScreenRoutes.LibraryMapTab.routeName) {
                LibraryWritePostScreen(navController)
            }

            // 쪽지작성 스크린
            composable(ScreenRoutes.LibraryMapTabScreenGroup.LibraryWritePost.routeName) {
                LibraryMapTabScreen(navController)
            }

            // 도서관별 쪽지 리스트 조회 페이지
            composable(ScreenRoutes.LibraryMapTabScreenGroup.LibraryPostList.routeName) {
                LibraryPostListScreen(navController)
            }


            // record tab RecordTabScreen
            composable(ScreenRoutes.RecordTab.routeName) {
                RecordTabScreen(navController)
            }


            // MyPageTab
            composable(ScreenRoutes.MyPageTab.routeName) {
                MyPageTabScreen(navController)
            }

            // myProfile
            composable(ScreenRoutes.MyPageScreenGroup.MyProfile.routeName) {
                MyProfileScreen(navController)
            }

            PostDetailNavGraph(navController)

        }
    }

}