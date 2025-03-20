package com.jeong.sesac.data.repository

import com.jeong.sesac.data.datasource.CommentDataSource
import com.jeong.sesac.feature.model.Comment
import com.jeong.sesac.feature.model.CommentWithUser
import com.jeong.sesac.feature.model.UserInfo
import com.jeong.sesac.feature.repository.ICommentRepository
import com.jeong.sesac.feature.repository.IUserRepository

class CommentRepositoryImpl(private val commentDataSource: CommentDataSource, private val userRepo: IUserRepository) :
    ICommentRepository {

    override suspend fun createComment(userId: String, postId: String, comment: String): Boolean {
        val userComment = Comment(
            id = "",
            userId = userId,
            noteId = postId,
            content = comment,
            createdAt = System.currentTimeMillis()
        )

        return commentDataSource.createComment(userId, postId, userComment)
    }

    override suspend fun getComments(userId: String, postId: String): Result<List<CommentWithUser>> {
        return commentDataSource.getComments(userId, postId).map { comments ->
            comments.map {comment ->
                val userInfo = userRepo.getUserInfo(comment.userId)
                CommentWithUser(
                    id = comment.id,
                    userInfo = UserInfo(
                        id = userInfo.id,
                        profile = userInfo.profile,
                        nickName = userInfo.nickName
                    ),
                    content = comment.content,
                    createdAt = comment.createdAt
                )
            }
        }.onFailure {
            throw Exception("${it.message}")
        }
    }

    override suspend fun updateComment(postId: String, commentId: String, content: String): Result<Unit> {
        return commentDataSource.updateComment(postId, commentId, content)
    }

    override suspend fun deleteComment(postId: String, commentId: String): Result<Unit> {
        return commentDataSource.deleteComment(postId, commentId)
    }
}