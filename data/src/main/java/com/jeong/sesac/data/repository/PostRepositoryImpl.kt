package com.jeong.sesac.data.repository

import com.jeong.sesac.data.datasource.PostDataSource
import com.jeong.sesac.feature.model.Post
import com.jeong.sesac.feature.model.PostWithUser
import com.jeong.sesac.feature.model.UserInfo
import com.jeong.sesac.feature.repository.IPostRepository
import com.jeong.sesac.feature.repository.IUserRepository

class PostRepositoryImpl(
    private val postDataSourceImpl: PostDataSource,
    private val UserRepo: IUserRepository
) : IPostRepository {

    override suspend fun createPost(post: Post): Result<Boolean> {
        val postInfo = Post(
            id = "",
            userId = post.userId,
            image = post.image,
            title = post.title,
            content = post.content,
            createdAt = post.createdAt,
            libraryName = post.libraryName,
            likes = emptyList(),
        )

        return postDataSourceImpl.createPost(postInfo)
    }

    override suspend fun updatePost(postId: String, post: Post): Result<Unit> {
        return postDataSourceImpl.updatePost(postId, post)
    }

    override suspend fun getPost(postId: String): Result<PostWithUser> {
        return postDataSourceImpl.getPost(postId).map { post ->
            val userInfo = UserRepo.getUserInfo(post.userId)
            PostWithUser(
                id = post.id,
                userInfo = UserInfo(
                    id = userInfo.id,
                    profile = userInfo.profile,
                    nickName = userInfo.nickName
                ),
                image = post.image,
                title = post.title,
                content = post.content,
                createdAt = post.createdAt,
                libraryName = post.libraryName,
                likes = post.likes
            )
        }.onFailure {
            throw Exception("${it.message}")
        }
    }

    override suspend fun deletePost(postId: String): Result<Unit> {
        return postDataSourceImpl.deletePost(postId)
    }
}