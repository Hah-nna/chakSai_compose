package com.jeong.sesac.feature.repository

import com.jeong.sesac.feature.model.UserInfo

interface IUserRepository {
    suspend fun setUser(nickname: String): Result<String>
    suspend fun checkDuplicateNickname(nickname : String): Result<Boolean>
    suspend fun getUserInfo(userId: String): UserInfo
}