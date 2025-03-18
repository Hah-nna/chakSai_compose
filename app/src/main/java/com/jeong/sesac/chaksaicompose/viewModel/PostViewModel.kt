package com.jeong.sesac.chaksaicompose.viewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeong.sesac.chaksaicompose.model.UiState
import com.jeong.sesac.feature.model.Post
import com.jeong.sesac.feature.model.PostWithUser
import com.jeong.sesac.feature.repository.IPostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PostViewModel(private val postRepo: IPostRepository) : ViewModel() {

        private val _uiState = MutableStateFlow<UiState<Boolean?>>(UiState.Loading)
        val uiState = _uiState.asStateFlow()

        /**
         *  선택된 post의 상태
         */
        private var _selectedPostState = MutableStateFlow<UiState<PostWithUser>>(UiState.Loading)
        val selectedPostState = _selectedPostState.asStateFlow()

        /**
         * post update, delete 상태
         * */
        private var _fetchPostState = MutableStateFlow<UiState<Unit>>(UiState.Loading)
        val fetchPostState = _fetchPostState.asStateFlow()

        @RequiresApi(Build.VERSION_CODES.O)
        fun createPost(
            userId: String,
            imgUri: String,
            title: String,
            content: String,
            libraryName: String
        ) {
            val post = Post(
                id = "",
                userId = userId,
                image = imgUri,
                title = title,
                content = content,
                libraryName = libraryName,
                likes = emptyList(),
                createdAt = System.currentTimeMillis(),
            )

            viewModelScope.launch {
                _uiState.value = UiState.Loading

                postRepo.createPost(post)
                    .onSuccess {
                        _uiState.value = UiState.Success(it)
                    }.onFailure {
                        _uiState.value = UiState.Error("다시시도해주세요")
                    }
            }
        }

        /**
         * post 선택
         */
        fun selectPost(postId: String) = viewModelScope.launch {
            _selectedPostState.value = UiState.Loading
            postRepo.getPost(postId)
                .onSuccess { post ->
                    _selectedPostState.value = UiState.Success(post)
                }.onFailure { error ->
                    _selectedPostState.value = UiState.Error("${error.message}")
                }
        }

        /**
         * post 업데이트
         * */
        fun updatePost(postId: String, post: Post) = viewModelScope.launch {
            _fetchPostState.value = UiState.Loading
            postRepo.updatePost(postId, post)
                .onSuccess { post ->
                    _fetchPostState.value = UiState.Success(Unit)
                }.onFailure {
                    _fetchPostState.value = UiState.Error("쪽지 업데이트 실패")
                }
        }

        fun deletePost(postId: String) = viewModelScope.launch {
            _fetchPostState.value = UiState.Loading
            postRepo.deletePost(postId)
                .onSuccess {
                    _fetchPostState.value = UiState.Success(Unit)
                }.onSuccess {
                    _fetchPostState.value = UiState.Error("쪽지 삭제 실패")
                }
        }
}