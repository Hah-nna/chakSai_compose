package com.jeong.sesac.feature.model

sealed class PostFilterType {
    data class ByLibrary(val libraryName: String): PostFilterType()
    data class ByLikes(val ascending: Boolean): PostFilterType()
    data class ByCreatedAt(val ascending: Boolean): PostFilterType()
    data object MyNotes: PostFilterType()
    data object MyLikedNotes: PostFilterType()
}