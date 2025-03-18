package com.jeong.sesac.data.datasource

import android.content.Context
import android.net.Uri
import android.util.Log
import android.webkit.MimeTypeMap
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.tasks.await
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream

class FirebaseStorageDataSourceImpl(private val context: Context) : FirebaseStorageDataSource {
    private val storageRef = Firebase.storage.reference

    override suspend fun createImg(uri: Uri, noteId: String): String {
        return runCatching {
            val tempFile = fileFromContentUri(context, uri)

            try {
                val fileName = createFileName(noteId)
                val imgRef = storageRef.child(fileName)

                imgRef.putFile(Uri.fromFile(tempFile)).await()
                imgRef.downloadUrl.await().toString()
            } finally {
                tempFile.delete()
            }
        }.onFailure { e -> Log.d("error!!!", "${e.message}") }.getOrThrow()
    }

    private fun fileFromContentUri(context: Context, uri: Uri): File {
        val fileExtension = getFileExtension(context, uri)
        val fileName = "temporary_file" + if (fileExtension != null) ".$fileExtension" else ""

        val tempFile = File(context.cacheDir, fileName)
        tempFile.createNewFile()

        try {
            val oStream = FileOutputStream(tempFile)
            val inputStream = context.contentResolver.openInputStream(uri)

            inputStream?.let {
                copy(inputStream, oStream)
            }

            oStream.flush()
        } catch (e: Exception) {
            println("error!!!! ${e.message}")
        }
        return tempFile
    }

    private fun getFileExtension(context: Context, uri: Uri): String? {
        val fileType: String? = context.contentResolver.getType(uri)
        return MimeTypeMap.getSingleton().getExtensionFromMimeType(fileType)
    }

    private fun copy(source: InputStream, target: OutputStream) {
        val buf = ByteArray(8192)
        var length: Int
        while (source.read(buf).also { length = it } > 0) {
            target.write(buf, 0, length)
        }
    }

    private fun createFileName(noteId: String): String {
//        val timeStamp = System.currentTimeMillis()
//        val date = SimpleDateFormat("yyyy/MM/dd", Locale.KOREA).format(Date(timeStamp))
        return "noteImg/${noteId}.jpg"
    }

    override suspend fun deleteImg(fileName: String): Result<Unit> {
        return runCatching {
            val imgRef = storageRef.child("noteImg/$fileName.jpg")
            imgRef
                .delete()
                .await()

        }.map { Unit }.onFailure {
            Log.e("error", "${it.message}")
        }

    }
}