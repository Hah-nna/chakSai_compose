package com.jeong.sesac.chaksaicompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.jeong.sesac.chaksaicompose.ui.MainEntryScreen
import com.jeong.sesac.chaksaicompose.ui.theme.ChakSaiComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChakSaiComposeTheme {
                MainEntryScreen()
            }
        }
    }
}