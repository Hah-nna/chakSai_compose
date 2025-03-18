package com.jeong.sesac.chaksaicompose.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeong.sesac.chaksaicompose.model.UiState
import com.jeong.sesac.feature.model.PostFilterType
import com.jeong.sesac.feature.model.PostWithUser
import com.jeong.sesac.feature.repository.IPostListRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PostListViewModel(private val postListRepo: IPostListRepository) : ViewModel(){

    // 이번주 인기 post
    private var _popularPostsState = MutableStateFlow<UiState<List<PostWithUser>>>(UiState.Loading)
    val popularPostsState get() = _popularPostsState.asStateFlow()

    // 최근 등록된 쪽지
    private var _newPostsState = MutableStateFlow<UiState<List<PostWithUser>>>(UiState.Loading)
    val newPostsState get() = _newPostsState.asStateFlow()

    /**
     * 도서관별 post 리스트 상태
     */
    private var _libraryPostsState = MutableStateFlow<UiState<List<PostWithUser>>>(UiState.Loading)
    val libraryPostsState get() = _libraryPostsState.asStateFlow()

    // 포스트 좋아요 상태
    private var _likeState = MutableStateFlow<UiState<Boolean>>(UiState.Loading)
    val likeState get() = _likeState.asStateFlow()


    fun getNewPostList(userId: String) = viewModelScope.launch {
        _newPostsState.value = UiState.Loading

        postListRepo.getPostList(PostFilterType.ByCreatedAt(true), userId)
            .onSuccess { posts ->
                _newPostsState.value = UiState.Success(posts)
            }.onFailure { error ->
                _newPostsState.value = UiState.Error("인기 노트 목록을 불러오는데 실패했습니다")
            }
    }

    fun getPopularNoteList(userId: String) = viewModelScope.launch {
        _popularPostsState.value = UiState.Loading

        postListRepo.getPostList(PostFilterType.ByCreatedAt(true), userId)
            .onSuccess { posts ->
                _popularPostsState.value = UiState.Success(posts)
            }.onFailure { error ->
                _popularPostsState.value = UiState.Error("인기 노트 목록을 불러오는데 실패했습니다")
            }
    }


    /**
     * 도서관별 쪽지 리스트 가져오기
     * */
    fun getLibraryPostList(libraryName: String) = viewModelScope.launch {
        _libraryPostsState.value = UiState.Loading
        postListRepo.getLibraryPostList(libraryName)
            .onSuccess { posts ->
                _libraryPostsState.value = UiState.Success(posts)
            }.onFailure { error ->
                _libraryPostsState.value = UiState.Error("노트를 불러오는데 실패했습니다")
            }
    }

    fun toggleLikes(noteId: String, userId: String) = viewModelScope.launch {
        _likeState.value = UiState.Loading
        postListRepo.toggleLike(noteId, userId)
            .onSuccess {
                _likeState.value = UiState.Success(it)

            }.onFailure { error ->
                _likeState.value = UiState.Error("좋아요에 실패했습니다\n다시시도해주세요")
            }
    }

}