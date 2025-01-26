package com.jeong.sesac.chaksaicompose.common

import android.app.Application

class ComposeNavigationApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        composeApplication = this
    }

    companion object {
        private lateinit var composeApplication: ComposeNavigationApplication
        fun getMyApplication() = composeApplication
    }
}