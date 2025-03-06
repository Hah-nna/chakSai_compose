package com.jeong.sesac.data.repository

import com.jeong.sesac.data.datasource.UserDataSource
import com.jeong.sesac.feature.model.ProviderType
import com.jeong.sesac.feature.model.User
import com.jeong.sesac.feature.model.UserInfo
import com.jeong.sesac.feature.repository.IUserRepository

class UserRepositoryImpl(private val userDataSourceImpl: UserDataSource) : IUserRepository {

    override suspend fun setUser(nickname: String): Result<String> {

        val userInfo = User(
            id = "",
            nickname = nickname,
            profile = "",
            // 이건 유저 등록할 떄 업데이트
            created_at = System.currentTimeMillis(),
            provider_info = ProviderType.KAKAO
        )
        return userDataSourceImpl.createUser(userInfo)
    }


    override suspend fun checkDuplicateNickname(nickname: String): Result<Boolean> {
        return userDataSourceImpl.getDuplicateNickname(nickname)
    }

    override suspend fun getUserInfo(userId: String): UserInfo {
        return userDataSourceImpl.getUserInfo(userId).let {
            it.copy(id = userId, profile = it.profile, nickName = it.nickName)
        }
    }
}