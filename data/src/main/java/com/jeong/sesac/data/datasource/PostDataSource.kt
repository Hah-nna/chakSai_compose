package com.jeong.sesac.data.datasource

import com.jeong.sesac.feature.model.Post

interface PostDataSource {
    suspend fun createPost(post : Post): Result<Boolean>
    suspend fun getPostList(): Result<List<Post>>
    suspend fun getLibraryPostList(libraryName: String) : Result<List<Post>>
    suspend fun getPost(postId: String): Result<Post>
    suspend fun updatePost(postId: String, post: Post): Result<Unit>
    suspend fun deletePost(postId: String): Result<Unit>
    suspend fun toggleLike(postId: String, userId:String): Result<Boolean>
}