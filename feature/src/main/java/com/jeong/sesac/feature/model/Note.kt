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

data class Comment(
    val id: String,
    val userId: String,
    val noteId: String,
    val content: String,
    val createdAt: String,
    val userInfo: UserInfo
)

data class User(
    val id: String,
    val profile: String,
    val nickname: String,
    val created_at: String,
    val provider_info: ProviderType = ProviderType.KAKAO
) {
    fun toMap(): Map<String, Any> {
        return mapOf(
            "id" to id,
            "nickname" to nickname,
            "profile" to profile,
            "created_at" to created_at,
            "provider_info" to provider_info.toString()
        )
    }

}


enum class ProviderType {
    KAKAO,
    NAVER,
    GOOGLE
}