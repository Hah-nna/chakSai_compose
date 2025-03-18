package com.jeong.sesac.chaksaicompose.validation

fun checkNicknameValid(nickname: String): Boolean {
    val onlyNumber = "[0-9]".toRegex()
    val validCharsRegex = "^[a-zA-Z가-힣0-9]+$".toRegex()

    return when {
        nickname.isEmpty() || nickname.length < 2 || nickname.length > 8 -> false
        !validCharsRegex.matches(nickname) -> false
        onlyNumber.matches(nickname) -> false
        else -> true
    }
}