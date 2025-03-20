package com.jeong.sesac.chaksaicompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeong.sesac.chaksaicompose.model.UiState
import com.jeong.sesac.feature.repository.IUserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserViewModel(private val userRepo: IUserRepository) : ViewModel() {
    private var _duplicateState = MutableStateFlow<UiState<Boolean>>(UiState.Loading)
    val duplicateState get() = _duplicateState.asStateFlow()

    private val _userCreateState = MutableStateFlow<UiState<String>>(UiState.Loading)
    val userCreateState get() = _userCreateState.asStateFlow()

    fun setUserInfo(nickname: String) = viewModelScope.launch {
        _userCreateState.value = UiState.Loading
        userRepo.setUser(nickname)
            .onSuccess {
                _userCreateState.value = UiState.Success(it)
            }.onFailure {
                UiState.Error("다시 시도해주세요")
            }
    }

    fun checkDuplicatedNickname(nickname: String) = viewModelScope.launch {
        _duplicateState.value = UiState.Loading
        userRepo.checkDuplicateNickname(nickname)
            .onSuccess {
                _duplicateState.value = UiState.Success(it)
            }.onFailure {
                _duplicateState.value = UiState.Error("다시 시도해주세요")
            }
    }
}