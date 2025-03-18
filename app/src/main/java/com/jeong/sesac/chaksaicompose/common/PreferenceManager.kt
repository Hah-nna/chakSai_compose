package com.jeong.sesac.chaksaicompose.common

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import android.util.Log

class AppPreferenceManager {

        companion object {
            private lateinit var manager : AppPreferenceManager
            private lateinit var sp : SharedPreferences
            private lateinit var spEditor : SharedPreferences.Editor

            private const val NICKNAME = "nickname"
            private const val USERID = "user_id"
            private const val LAST_LAT = "last_lat"
            private const val LAST_LNG = "last_lng"


            fun getInstance(context : Context) : AppPreferenceManager {
                if(this::manager.isInitialized) {
                    return manager
                } else {
                    sp = PreferenceManager.getDefaultSharedPreferences(context)
                    spEditor  = sp.edit()
                    manager = AppPreferenceManager()
                }
                return manager
            }

        }

        var nickName : String
            get() = sp.getString(NICKNAME, "").toString()

            set(value) {
                with(spEditor) {
                    putString(NICKNAME, value)
                    Log.d("닉네임 set", nickName)
                    apply()
                }
            }

    var userId : String
        get() = sp.getString(USERID, "").toString()

        set(value) {
            with(spEditor) {
                putString(USERID, value)
                Log.d("userid set", userId)
                apply()
            }
        }

        var lastLat : Double
            get() = sp.getString(LAST_LAT, "37.566535")?.toDouble() ?: 37.566535
            set(value) {
                with(spEditor) {
                    putString(LAST_LAT, value.toString())
                    apply()
                }
            }


        var lastLng : Double
            get() = sp.getString(LAST_LNG, "126.977969")?.toDouble() ?: 126.977969
            set(value) {
                with(spEditor) {
                    putString(LAST_LNG, value.toString())
                    apply()
                }
            }
}