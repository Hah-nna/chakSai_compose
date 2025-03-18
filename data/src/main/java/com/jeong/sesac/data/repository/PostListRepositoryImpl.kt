package com.jeong.sesac.data.repository

import android.util.Log
import com.jeong.sesac.data.datasource.PostDataSource
import com.jeong.sesac.feature.model.PostFilterType
import com.jeong.sesac.feature.model.PostWithUser
import com.jeong.sesac.feature.model.UserInfo
import com.jeong.sesac.feature.repository.IPostListRepository
import com.jeong.sesac.feature.repository.IUserRepository

class PostListRepositoryImpl(
    private val postDataSourceImpl: PostDataSource,
    private val userRepo: IUserRepository
) : IPostListRepository {

    override suspend fun getPostList(
        filterType: PostFilterType,
        userId: String
    ): Result<List<PostWithUser>> {
        return postDataSourceImpl.getPostList().fold(
            onSuccess = { postList ->
                // 필터 타입에 따라 노트 리스트 필터링 및 정렬
                val filteredPostList = when (filterType) {
                    is PostFilterType.ByCreatedAt -> {
                        if (filterType.ascending) {
                            postList.sortedBy { it.createdAt }
                        } else {
                            postList.sortedByDescending { it.createdAt }
                        }
                    }

                    is PostFilterType.ByLikes -> {
                        if (filterType.ascending) {
                            postList.sortedBy { it.likes.size }
                        } else {
                            postList.sortedByDescending { it.likes.size }
                        }
                    }

                    is PostFilterType.ByLibrary -> {
                        postList.filter { it.libraryName == filterType.libraryName }
                    }

                    is PostFilterType.MyNotes -> {
                        postList.filter { it.userId == userId }
                            .sortedByDescending { it.createdAt }
                    }

                    is PostFilterType.MyLikedNotes -> {
                        postList.filter { it.likes.contains(userId) }
                            .sortedByDescending { it.createdAt }
                    }

                }

                // 필터링된 노트에 사용자 정보 추가
                val postsWithUser = filteredPostList.map { post ->
                    val userInfo = userRepo.getUserInfo(post.userId)
                    PostWithUser(
                        id = post.id,
                        userInfo = UserInfo(
                            id = userInfo.id,
                            profile = userInfo.profile,
                            nickName = userInfo.nickName
                        ),
                        image = post.image,
                        title = post.title,
                        createdAt = post.createdAt,
                        libraryName = post.libraryName,
                        content = post.content,
                        likes = post.likes
                    )
                }
                Result.success(postsWithUser)
            },
            onFailure = { error ->
                Log.e("NoteListRepository", "error getNoteList: ${error.message}")
                Result.failure(error)
            }
        )
    }

    override suspend fun getLibraryPostList(libraryName: String): Result<List<PostWithUser>> {
        return postDataSourceImpl.getLibraryPostList(libraryName).map { posts ->
            posts.map { post ->
                val userInfo = userRepo.getUserInfo(post.userId)
                PostWithUser(
                    id = post.id,
                    userInfo = UserInfo(
                        id = userInfo.id,
                        profile = userInfo.profile,
                        nickName = userInfo.nickName
                    ),
                    image = post.image,
                    title = post.title,
                    createdAt = post.createdAt,
                    libraryName = post.libraryName,
                    content = post.content,
                    likes = post.likes
                )
            }
        }.onFailure {
            throw Exception("도서관 목록을 가져오는데 실패했습니다 ${it.message}")
        }
    }


    override suspend fun toggleLike(postId: String, userId: String): Result<Boolean> {
        return postDataSourceImpl.toggleLike(postId, userId)
    }
}