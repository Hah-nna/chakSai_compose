package com.jeong.sesac.data.datasource

import com.jeong.sesac.feature.model.User
import com.jeong.sesac.feature.model.UserInfo

interface UserDataSource {
    suspend fun createUser(userInfo: User) : Result<String>
    suspend fun getDuplicateNickname(nickname : String): Result<Boolean>
    suspend fun getUserInfo(userId: String): UserInfo
}