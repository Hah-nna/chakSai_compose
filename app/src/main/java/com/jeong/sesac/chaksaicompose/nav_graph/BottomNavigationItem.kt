package com.jeong.sesac.chaksaicompose.nav_graph

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.jeong.sesac.chaksaicompose.R

data class BottomNavigationItem (
    val tabName : String = "",
    val icon : Int = 0,
    val route : String = ""
) {
    fun renderBottomNavigationItems() : List<BottomNavigationItem>{
        return listOf(
            // home tab
            BottomNavigationItem(
                tabName = ScreenRoutes.HomeTab.routeName,
                icon = R.drawable.ic_home,
                route = ScreenRoutes.HomeTab.routeName
            ),
            // library map tab
            BottomNavigationItem(
                tabName = ScreenRoutes.LibraryMapTab.routeName,
                icon = R.drawable.ic_library_map,
                route = ScreenRoutes.LibraryMapTab.routeName
            ),
            // record
            BottomNavigationItem(
                tabName = ScreenRoutes.RecordTab.routeName,
                icon = R.drawable.ic_record,
                route = ScreenRoutes.RecordTab.routeName
            ),
            // 채팅
            BottomNavigationItem(
                tabName = ScreenRoutes.ChattingTab.routeName,
                icon = R.drawable.ic_chat,
                route = ScreenRoutes.ChattingTab.routeName
            ),
            // 마이 페이지
            BottomNavigationItem(
                tabName = ScreenRoutes.MyPageTab.routeName,
                icon = R.drawable.ic_my_page,
                route = ScreenRoutes.MyPageTab.routeName
            ),

        )
    }
}