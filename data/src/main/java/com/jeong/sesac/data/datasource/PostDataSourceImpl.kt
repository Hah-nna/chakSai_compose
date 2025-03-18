package com.jeong.sesac.data.datasource

import android.net.Uri
import android.util.Log
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.jeong.sesac.feature.model.Post
import kotlinx.coroutines.tasks.await

class PostDataSourceImpl(private val storageDataSource: FirebaseStorageDataSource) : PostDataSource{

    private val postCollectionRef = Firebase.firestore.collection("notes")

    override suspend fun createPost(post: Post): Result<Boolean> {
        return runCatching {
            val noteDocRef = postCollectionRef.add(post).await()
            val noteDocRefId = noteDocRef.id
            val imgUri = if (post.image.isNotEmpty()) storageDataSource.createImg(
                Uri.parse(post.image),
                noteDocRefId
            ) else ""
            postCollectionRef.document(noteDocRefId)
                .update(
                    mapOf(
                        "id" to noteDocRefId,
                        "userId" to post.userId,
                        "image" to imgUri,
                        "createdAt" to post.createdAt
                    )
                ).await()
            true
        }.onFailure { e ->
            Log.d("create note error", "${e.message}, ${e.cause}")
        }
    }

    override suspend fun getPostList(): Result<List<Post>> {
        return runCatching {
            postCollectionRef
                .orderBy("createdAt", Query.Direction.DESCENDING)
                .get()
                .await()
                .documents.mapNotNull { it.toObject(Post::class.java) }
        }.onFailure { e ->
            Log.e("getNotelist error!!!", "${e.message}")
            throw Exception("포스트를 가져오는데 실패했습니다 ${e.message}")
        }
    }


    override suspend fun getLibraryPostList(libraryName: String): Result<List<Post>> {
        return runCatching {
            val libraryNote = postCollectionRef.whereEqualTo("libraryName", libraryName)
                .orderBy("createdAt", Query.Direction.DESCENDING)
                .get()
                .await()

            libraryNote.documents.mapNotNull {
                Log.d("도서관별 노트 데이터", "${it.data}")
                it.toObject(Post::class.java)

            }
        }.onFailure { e ->
            Log.e("도서관별 쪽지 가져오기 error", "${e.message}")
        }

    }

    override suspend fun getPost(postId: String): Result<Post> {
        return runCatching {
            val selectedPost = postCollectionRef.whereEqualTo("id", postId)
                .get()
                .await()

            Log.d("selectedNote", "$selectedPost")

            selectedPost.documents.mapNotNull { doc ->
                Log.d("Firebase Debug", "문서 데이터: ${doc.data}")
                doc.toObject(Post::class.java)
            }.single()
        }.onFailure { e ->
            Log.e("쪽지 가져오기 error", "${e.message}")
            throw Exception("선택한 쪽지내용 가져오기 실패 ${e.message}")
        }
    }

    override suspend fun updatePost(postId: String, post: Post): Result<Unit> {
        return runCatching {
            val updates = mutableMapOf<String, Any>()

            if (post.title.isNotEmpty()) updates["title"] = post.title
            if (post.content.isNotEmpty()) updates["content"] = post.content
            if (post.image.isNotEmpty()) updates["image"] = post.image

            updates["createdAt"] = System.currentTimeMillis()

            postCollectionRef.document(postId)
                .update(updates)
                .await()
        }.map { Unit }
            .onFailure { e ->
                Log.e("note update 실패", "${e.message}")
            }
    }

    override suspend fun deletePost(postId: String): Result<Unit> {
        return runCatching {
            storageDataSource.deleteImg(postId).onSuccess {
                postCollectionRef.document(postId)
                    .delete()
                    .await()
            }
        }.map { Unit }
            .onFailure { e ->
                Log.e("delete 실패", "${e.message}")
            }
    }

    override suspend fun toggleLike(postId: String, userId: String): Result<Boolean> {
        return runCatching {
            val noteRef = postCollectionRef.document(postId)
            Firebase.firestore.runTransaction { transaction ->
                val postSnapshot = transaction.get(noteRef)
                val post = postSnapshot.toObject(Post::class.java)

                post?.let {
                    val likeList = post.likes.toMutableList()
                    val isLiked = likeList.contains(userId)

                    if (isLiked) likeList.remove(userId) else likeList.add(userId)

                    transaction.update(noteRef, "likes", likeList)
                    !isLiked
                } ?: throw Exception("해당 쪽지를 찾을 수 없습니다")
            }.await()
        }.onFailure {
            Log.e("toggle 라이크 실패", "${it.message}, ${it.cause}")
            throw Exception("좋아요 토글 실패")
        }
    }
}