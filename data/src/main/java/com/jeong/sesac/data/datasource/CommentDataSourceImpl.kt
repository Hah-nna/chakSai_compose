package com.jeong.sesac.data.datasource

import android.util.Log
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.jeong.sesac.feature.model.Comment
import kotlinx.coroutines.tasks.await

class CommentDataSourceImpl : CommentDataSource {
    private val postCollectionRef = Firebase.firestore.collection("notes")

    override suspend fun createComment(
        userId: String,
        postId: String,
        comment: Comment
    ): Boolean {
        return runCatching {
            val commentRef = postCollectionRef
                .document(postId)
                .collection("comments")
                .add(comment.copy(userId = userId))
                .await()

            postCollectionRef
                .document(postId)
                .collection("comments")
                .document(commentRef.id)
                .update("id", commentRef.id)
                .await()

            true
        }.getOrElse { e ->
            Log.e("코멘트생성에러", "코멘트생성에러: ${e.message}")
            false
        }
    }

    override suspend fun getComments(userId: String, postId: String): Result<List<Comment>> {
        return runCatching {
            postCollectionRef
                .document(postId)
                .collection("comments")
                .orderBy("createdAt", Query.Direction.DESCENDING)
                .get()
                .await()
                .documents.mapNotNull {  it.toObject(Comment::class.java) }

        }.onFailure { e ->
            Log.e("코멘트가져오기실패", "코멘트가져오기실패: ${e.message}")
            "코멘트 가져오기 실패"
        }
    }

    override suspend fun updateComment(
        postId: String,
        commentId: String,
        content: String
    ): Result<Unit> {
        return runCatching {
            postCollectionRef.document(postId)
                .collection("comments")
                .document(commentId)
                .update("content", content)
                .await()
        }.map { Unit }.onFailure { e ->
            Log.e("코멘트 업데이트실패", "${e.message}")
        }
    }

    override suspend fun deleteComment(postId: String, commentId: String): Result<Unit> {
        return runCatching {
            postCollectionRef.document(postId)
                .collection("comments")
                .document(commentId)
                .delete()
                .await()

        }.map { Unit }
            .onFailure { e ->
                Log.e("코멘트 삭제실패", "${e.message}")
            }
    }
}
