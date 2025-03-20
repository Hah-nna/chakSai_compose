package com.jeong.sesac.chaksaicompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeong.sesac.chaksaicompose.model.UiState
import com.jeong.sesac.feature.model.Comment
import com.jeong.sesac.feature.model.CommentWithUser
import com.jeong.sesac.feature.repository.ICommentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CommentViewModel(private val commentRepo: ICommentRepository) : ViewModel() {
    private var _commentState = MutableStateFlow<UiState<Boolean>>(UiState.Loading)
    val commentState = _commentState.asStateFlow()

    private var _commentListState =
        MutableStateFlow<UiState<List<CommentWithUser>>>(UiState.Loading)
    val commentListState = _commentListState.asStateFlow()

    private var _commentUpdateState = MutableStateFlow<UiState<Unit>>(UiState.Loading)
    val commentUpdateState = _commentUpdateState.asStateFlow()

    private var _commentDeleteState = MutableStateFlow<UiState<Unit>>(UiState.Loading)
    val commentDeleteState = _commentDeleteState.asStateFlow()

    fun createComment(userId: String, postId: String, comment: String) {
        viewModelScope.launch {
            _commentState.value = UiState.Loading

            val isSuccess = commentRepo.createComment(userId, postId, comment)
            _commentState.value =
                if (isSuccess) {
                    getComments(userId, postId)
                    UiState.Success(true)
                } else UiState.Error("다시 시도해주세요")
        }
    }

    fun getComments(userId: String, postId: String) {
        viewModelScope.launch {
            _commentListState.value = UiState.Loading
            commentRepo.getComments(userId, postId)
                .onSuccess {
                    _commentListState.value = UiState.Success(it.reversed())
                }.onFailure {
                    _commentListState.value = UiState.Error("다시 시도해주세요")
                }

        }
    }


    fun updateComment(userId: String, postId: String, commentId: String, content: String) {
        viewModelScope.launch {
            _commentUpdateState.value = UiState.Loading

            commentRepo.updateComment(postId, commentId, content)
                .onSuccess {
                    _commentUpdateState.value = UiState.Success(Unit)
                    getComments(userId, postId)
                }.onFailure {
                    _commentUpdateState.value = UiState.Error("댓글 수정에 실패했습니다")
                }
        }
    }

    fun deleteComment(userId: String, postId: String, commentId: String) {
        viewModelScope.launch {
            _commentDeleteState.value = UiState.Loading
            commentRepo.deleteComment(postId, commentId)
                .onSuccess {
                    _commentDeleteState.value = UiState.Success(Unit)
                    getComments(userId, postId)
                }.onFailure {
                    _commentDeleteState.value = UiState.Error("댓글 삭제에 실패했습니다\n다시시도해주세요")
                }
        }
    }
}