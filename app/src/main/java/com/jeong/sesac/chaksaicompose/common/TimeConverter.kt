package com.jeong.sesac.chaksaicompose.common

fun Long.toTimeConverter(): String {
    val currentTime = System.currentTimeMillis()
    val timeDifference = currentTime - this

    val minutes = timeDifference / (1000 * 60)
    val hours = minutes / 60
    val days = hours / 24
    val months = days / 30
    val years = days / 365

    return when {
        minutes < 1 -> "방금 전"
        minutes < 5 -> "5분 전"
        minutes < 10 -> "10분 전"
        minutes < 20 -> "20분 전"
        minutes < 30 -> "30분 전"
        minutes < 60 -> "${minutes}분 전"
        hours < 24 -> "${hours}시간 전"
        days < 7 -> "${days}일 전"
        days < 30 -> "${days / 7}주 전"
        days < 365 -> "${months}주 전"
        else -> "${years}년 전"

    }
}