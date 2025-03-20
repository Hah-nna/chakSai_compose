package com.jeong.sesac.chaksaicompose.viewmodel.viewmodel_factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jeong.sesac.chaksaicompose.common.ChakSaiApplication
import com.jeong.sesac.chaksaicompose.viewmodel.CommentViewModel
import com.jeong.sesac.chaksaicompose.viewmodel.PostListViewModel
import com.jeong.sesac.chaksaicompose.viewmodel.PostViewModel
import com.jeong.sesac.chaksaicompose.viewmodel.UserViewModel
import com.jeong.sesac.data.datasource.CommentDataSourceImpl
import com.jeong.sesac.data.datasource.FirebaseStorageDataSourceImpl
import com.jeong.sesac.data.datasource.PostDataSourceImpl
import com.jeong.sesac.data.datasource.UserDataSourceImpl
import com.jeong.sesac.data.repository.CommentRepositoryImpl
import com.jeong.sesac.data.repository.PostListRepositoryImpl
import com.jeong.sesac.data.repository.PostRepositoryImpl
import com.jeong.sesac.data.repository.UserRepositoryImpl

@Suppress("UNCHECKED_CAST")
val appViewModelFactory = object : ViewModelProvider.Factory {
    val context = ChakSaiApplication.getInstance()
    override fun < T : ViewModel>  create(modelClass: Class<T>): T =
        with(modelClass) {
            when {
                isAssignableFrom(UserViewModel::class.java) ->
                    UserViewModel(UserRepositoryImpl(UserDataSourceImpl()))
                isAssignableFrom(PostViewModel::class.java) -> {
                    PostViewModel(PostRepositoryImpl(PostDataSourceImpl(FirebaseStorageDataSourceImpl(context)),  UserRepositoryImpl(
                        UserDataSourceImpl())))
                }
                isAssignableFrom(PostListViewModel::class.java) -> {
                    PostListViewModel(PostListRepositoryImpl(PostDataSourceImpl(FirebaseStorageDataSourceImpl(context)),  UserRepositoryImpl(
                        UserDataSourceImpl())))
                }
                isAssignableFrom(CommentViewModel::class.java) -> {
                    CommentViewModel(CommentRepositoryImpl(CommentDataSourceImpl(), UserRepositoryImpl(UserDataSourceImpl())))
                }
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}