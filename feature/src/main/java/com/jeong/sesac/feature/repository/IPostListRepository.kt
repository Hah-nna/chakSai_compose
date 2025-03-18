package com.jeong.sesac.feature.repository

import com.jeong.sesac.feature.model.PostFilterType
import com.jeong.sesac.feature.model.PostWithUser

interface IPostListRepository {
    suspend fun getPostList(filterType: PostFilterType, userId: String): Result<List<PostWithUser>>
    suspend fun getLibraryPostList(libraryName: String): Result<List<PostWithUser>>
    suspend fun toggleLike(postId: String, userId: String): Result<Boolean>
}