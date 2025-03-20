package com.jeong.sesac.data.datasource

import com.jeong.sesac.feature.model.Comment

interface CommentDataSource {
    suspend fun createComment(userId: String, postId: String, comment: Comment): Boolean
    suspend fun getComments(userId: String, postId: String): Result<List<Comment>>
    suspend fun updateComment(postId: String, commentId: String, content: String) : Result<Unit>
    suspend fun deleteComment(postId: String, commentId: String): Result<Unit>
}