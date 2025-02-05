package com.jeong.sesac.chaksaicompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jeong.sesac.chaksaicompose.ui.MainEntryScreen
import com.jeong.sesac.chaksaicompose.ui.login.LoginScreen
import com.jeong.sesac.chaksaicompose.ui.theme.ChakSaiComposeTheme

class LoginActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChakSaiComposeTheme {
                LoginScreen()
            }
        }
    }
}