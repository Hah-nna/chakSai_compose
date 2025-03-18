package com.jeong.sesac.feature.repository

import com.jeong.sesac.feature.model.Post
import com.jeong.sesac.feature.model.PostWithUser


interface IPostRepository{
    suspend fun createPost(post: Post): Result<Boolean>
    suspend fun updatePost(postId: String, post: Post): Result<Unit>
    suspend fun getPost(postId: String): Result<PostWithUser>
    suspend fun deletePost(postId: String): Result<Unit>
}