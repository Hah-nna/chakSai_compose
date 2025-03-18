package com.jeong.sesac.chaksaicompose.nav_graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.jeong.sesac.chaksaicompose.ui.my_page.MyPageTabScreen
import com.jeong.sesac.chaksaicompose.ui.my_page.MyProfileScreen

fun NavGraphBuilder.myPageNavGraph(
    onNavigationUp: () -> Unit,
    onNavigationToEditMyInfo: () -> Unit,
    onNavigationToContact: () -> Unit,
    onNavigationToPolicies: () -> Unit
) {
    navigation(
        startDestination = ScreenRoutes.MyPageScreenGroup.MyProfile.routeName,
        route = "my_page_nav_graph"
    ) {
        composable(route = ScreenRoutes.MyPageScreenGroup.MyProfile.routeName) {
            MyPageTabScreen(
                onBackClick = onNavigationUp,
                onEditMyInfoClick = { onNavigationToEditMyInfo() },
                onContactClick = { onNavigationToContact() },
                onPoliciesClick = { onNavigationToPolicies() }
            )
        }

        composable(route = ScreenRoutes.MyPageScreenGroup.EditMyInfo.routeName) {
            MyProfileScreen(
                onBackClick = onNavigationUp
            )
        }

        // contact는 구글form연결 ㅎ...
        // 앱 정책은 노션 연결하기(딥링크?)

    }
}