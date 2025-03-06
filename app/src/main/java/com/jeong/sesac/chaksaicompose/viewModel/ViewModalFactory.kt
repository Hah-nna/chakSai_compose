package com.jeong.sesac.chaksaicompose.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jeong.sesac.data.datasource.UserDataSourceImpl
import com.jeong.sesac.data.repository.UserRepositoryImpl

@Suppress("UNCHECKED_CAST")
val appViewModelFactory = object : ViewModelProvider.Factory {
//    val context = ChakSaiClass.getContext()
    override fun < T : ViewModel>  create(modelClass: Class<T>): T =
        with(modelClass) {
            when {
                isAssignableFrom(UserViewModel::class.java) ->
                    UserViewModel(UserRepositoryImpl(UserDataSourceImpl()))
//                isAssignableFrom(KakaoMapViewModel::class.java) -> {
//                    val kakaoService = KakaoMapManager.getInstance()
//                    KakaoMapViewModel(KakaoMapRepositoryImpl(KakaoMapDataSourceImpl(kakaoService)), LBSRepositoryImpl(context))
//                }
//                isAssignableFrom(NoteViewModel::class.java) -> {
//                    NoteViewModel(NoteRepositoryImpl(NoteDataSourceImpl(FireBaseStorageDataSourceImpl(context)),  UserRepositoryImpl(
//                        UserDataSourceImpl()
//                    )
//                    ))
//                }
//
//                isAssignableFrom(NoteListViewModel::class.java) -> {
//                    NoteListViewModel(NoteListRepositoryImpl(NoteDataSourceImpl(FireBaseStorageDataSourceImpl(context)),  UserRepositoryImpl(
//                        UserDataSourceImpl()
//                    )
//                    ))
//                }
//
//                isAssignableFrom(CommentViewModel::class.java) -> {
//                    CommentViewModel(CommentRepositoryImpl(CommentFirebaseDataSourceImpl(), UserRepositoryImpl(
//                        UserDataSourceImpl()
//                    )
//                    ))
//                }
//                isAssignableFrom(BookViewModel::class.java) -> {
//                    val openApiService = OpenAPIManager.getInstance()
//                    BookViewModel((BookRepositoryImpl(BookDataSourceImpl(openApiService), UserRepositoryImpl(
//                        UserDataSourceImpl()
//                    )
//                    )), OpenApiRepositoryImpl(
//                        OpenAPIDataSourceImpl(openApiService)
//                    ))
//                }
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}