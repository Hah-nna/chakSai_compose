package com.jeong.sesac.chaksaicompose

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jeong.sesac.chaksaicompose.common.AppPreferenceManager
import com.jeong.sesac.chaksaicompose.ui.MainEntryScreen
import com.jeong.sesac.chaksaicompose.ui.splash.SplashScreen
import com.jeong.sesac.chaksaicompose.ui.theme.ChakSaiComposeTheme

class SplashActivity : ComponentActivity() {
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChakSaiComposeTheme() {
                SplashScreen()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("SplashActivity create", "create")
        handler.postDelayed({
            val preferences = AppPreferenceManager.getInstance(this)
            if (preferences.nickName.isEmpty()) {
                Intent(this@SplashActivity, LoginActivity::class.java).run {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(this)
                }
                finish()
            } else {
                Intent(this@SplashActivity, MainActivity::class.java).run {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(this)
                }
                finish()
            }
        }, 1500)
    }

}