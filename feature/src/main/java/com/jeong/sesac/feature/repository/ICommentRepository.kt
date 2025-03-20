package com.jeong.sesac.feature.repository

import com.jeong.sesac.feature.model.Comment
import com.jeong.sesac.feature.model.CommentWithUser

interface ICommentRepository {
    suspend fun createComment(userId: String, postId: String, comment: String): Boolean
    suspend fun getComments(userId: String, postId: String): Result<List<CommentWithUser>>
    suspend fun updateComment(postId: String, commentId: String, content: String): Result<Unit>
    suspend fun deleteComment(postId: String, commentId: String): Result<Unit>
}