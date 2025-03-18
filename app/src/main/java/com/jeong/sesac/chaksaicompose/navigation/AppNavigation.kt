package com.jeong.sesac.chaksaicompose.nav_graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jeong.sesac.chaksaicompose.common.AppPreferenceManager
import com.jeong.sesac.chaksaicompose.navigation.MainNavigation
import com.jeong.sesac.chaksaicompose.ui.login.LoginScreen
import com.jeong.sesac.chaksaicompose.ui.splash.SplashScreen
import kotlinx.coroutines.delay
import kotlinx.serialization.Serializable

@Composable
fun AppNavigation(preferenceManager: AppPreferenceManager) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Splash
    ) {
        // 스플래시
        composable<Splash> {
            SplashScreen()
            LaunchedEffect(Unit) {
                delay(1500)
                if (preferenceManager.nickName.isEmpty()) {
                    navController.navigate(Login) {
                        popUpTo(Splash) { inclusive = true }
                    }
                } else {
                    navController.navigate(Main) {
                        popUpTo(Splash) { inclusive = true }
                    }
                }
            }
        }

        // 회원가입 화면
        composable<Login> {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Main) {
                        popUpTo(Login) { inclusive = true }
                    }
                }
            )
        }

        // 메인 네비게이샨
        composable<Main> {
            MainNavigation()
        }
    }
}

@Serializable
object Splash

@Serializable
object Login

@Serializable
object Main