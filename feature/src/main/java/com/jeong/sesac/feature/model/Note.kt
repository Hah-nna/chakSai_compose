package com.jeong.sesac.feature.model

import java.util.Date

data class UserInfo(
    val id: String,
    val profile: String,
    val nickName: String
)

data class NoteWithUser(
    val id: String,
    val userInfo: UserInfo,
    val image: Int,
    val title: String,
    val createdAt: Date,
    val libraryName: String,
    val content: String,
    val likes: Int,
)