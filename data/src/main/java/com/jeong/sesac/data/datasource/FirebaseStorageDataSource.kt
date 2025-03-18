package com.jeong.sesac.data.datasource

import android.net.Uri

interface FirebaseStorageDataSource {
    suspend fun createImg(uri : Uri, noteId: String): String
    suspend fun deleteImg(fileName: String): Result<Unit>
}