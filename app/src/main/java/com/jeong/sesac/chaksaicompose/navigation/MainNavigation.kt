package com.jeong.sesac.chaksaicompose.navigation

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
import androidx.compose.ui.platform.LocalContext
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
import com.jeong.sesac.chaksaicompose.common.AppPreferenceManager
import com.jeong.sesac.chaksaicompose.nav_graph.BottomNavigationItem
import com.jeong.sesac.chaksaicompose.nav_graph.HomeBaseRoute
import com.jeong.sesac.chaksaicompose.nav_graph.NewPosts
import com.jeong.sesac.chaksaicompose.nav_graph.PopularPosts
import com.jeong.sesac.chaksaicompose.nav_graph.PostDetail
import com.jeong.sesac.chaksaicompose.nav_graph.ScreenRoutes
import com.jeong.sesac.chaksaicompose.nav_graph.homeNavGraph
import com.jeong.sesac.chaksaicompose.nav_graph.libraryMapGraph
import com.jeong.sesac.chaksaicompose.nav_graph.myPageNavGraph
import com.jeong.sesac.chaksaicompose.nav_graph.postDetailNavGraph
import com.jeong.sesac.chaksaicompose.ui.record.RecordTabScreen


@Preview(showBackground = true)
@Composable
fun MainNavigation() {
    val context = LocalContext.current
    val preference = AppPreferenceManager.getInstance(context)
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    fun onBackClick() {
        navController.navigateUp()
    }

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
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = HomeBaseRoute,
            modifier = Modifier.padding(
                start = dimensionResource(R.dimen.basicPadding),
                end = dimensionResource(R.dimen.basicPadding),
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding()
            )
        ) {

            // home tab navGraph
            homeNavGraph(
                preference = preference,
                onNavigationUp = { onBackClick() },
                onNavigationToNewPostList = { navController.navigate(NewPosts) },
                onNavigationToPopularNotes = { navController.navigate(PopularPosts) },
                onNavigationToDetailPost = { postId: String ->
                    navController.navigate(
                        PostDetail(
                            postId
                        )
                    )
                }
            )
            // map tab navGraph
            libraryMapGraph(
                onNavigationUp = { onBackClick() },
                onNavigationToPosts = { navController.navigate(ScreenRoutes.LibraryMapTabScreenGroup.LibraryPostList) },
                onNavigationToWritePost = { navController.navigate(ScreenRoutes.LibraryMapTabScreenGroup.LibraryWritePost) },
                onNavigationToBookReviews = { navController.navigate(ScreenRoutes.LibraryMapTabScreenGroup.LibraryBookReviewList) },
                onNavigationToCreateBook = { navController.navigate(ScreenRoutes.LibraryMapTabScreenGroup.LibraryWriteBook) },
                onNavigationToEditBookReview = { navController.navigate(ScreenRoutes.LibraryMapTabScreenGroup.LibraryEditBookReview) },
                onNavigationToDetailPost = { postId: String -> navController.navigate("post_detail_nav_graph/${postId}") }
            )

            // record tab navGraph
            composable(ScreenRoutes.RecordTab.routeName) {
                RecordTabScreen(navController)
            }

            // MyPage navGraph
            myPageNavGraph(
                onNavigationUp = { onBackClick() },
                onNavigationToEditMyInfo = { navController.navigate(ScreenRoutes.MyPageScreenGroup.EditMyInfo) },
                onNavigationToContact = {},
                onNavigationToPolicies = {}
            )

            // post detail navGraph
            postDetailNavGraph(
                onNavigationUp = { onBackClick() },
                onNavigationToEditPost = { navController.navigate(ScreenRoutes.PostDetailScreenGroup.EditPostScreen) },
                onNavigationToEditComment = { navController.navigate(ScreenRoutes.PostDetailScreenGroup.EditCommentScreen) }
            )
        }
    }

}

