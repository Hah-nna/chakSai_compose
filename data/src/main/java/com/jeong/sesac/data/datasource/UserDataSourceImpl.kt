package com.jeong.sesac.data.datasource

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.jeong.sesac.feature.model.User
import com.jeong.sesac.feature.model.UserInfo
import kotlinx.coroutines.tasks.await


class UserDataSourceImpl : UserDataSource {
    private val userCollectionRef = Firebase.firestore.collection("users")

    override suspend fun createUser(userInfo: User): Result<String> {
        return runCatching {
            val docRef = userCollectionRef.add(userInfo.toMap()).await()
            val docRefId = docRef.id

            userCollectionRef.document(docRefId)
                .update("id", docRefId)
                .await()
            docRefId
        }.onFailure { e ->
            Log.d("login error!", "${e.message}, ${e.cause}")
            throw Exception(e.message, e.cause)
        }
    }


    override suspend fun getUserInfo(userId: String): UserInfo {
        return try {
            Log.d("getUserInfo userId", "${userId}")
            val userDoc = userCollectionRef.document(userId).get().await()
            Log.d("DEBUG", "Document data: ${userDoc.data}")
            UserInfo(
                id = userId,
                nickName = userDoc["nickname"].toString(),
                profile = userDoc["profile"].toString()
            )
        } catch (e: Exception) {
            Log.d("error!!!!!!", "${e.message}")
            throw e
        }
    }

    override suspend fun getDuplicateNickname(nickname: String): Result<Boolean> {
        return runCatching {
            val result = userCollectionRef
                .whereEqualTo("nickname", nickname)
                .get()
                .await()
            result.size() > 0
        }.onFailure { e ->
            Log.d("login error!", "${e.message}, ${e.cause}")
            throw Exception(e.message, e.cause)
        }
    }
}