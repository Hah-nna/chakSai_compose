package com.jeong.sesac.chaksaicompose.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.jeong.sesac.chaksaicompose.R

const val lineHeightPercentage = 1.5f

val pretendardFontFamily = FontFamily(
    Font(R.font.pretendard, FontWeight.Normal)
)

val AppTypography = Typography(
    titleLarge = TextStyle(
        fontFamily = pretendardFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 22.sp,
        lineHeight = 24.sp * lineHeightPercentage
    ),
    titleMedium = TextStyle(
        fontFamily = pretendardFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        lineHeight = 18.sp * lineHeightPercentage
    ),
    titleSmall = TextStyle(
        fontFamily = pretendardFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 14.sp * lineHeightPercentage
    ),
    // button
    labelLarge = TextStyle(
        fontFamily = pretendardFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = pretendardFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = pretendardFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
    ),
    // body
    bodyLarge = TextStyle(
        fontFamily = pretendardFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 18.sp * lineHeightPercentage
    ),
    bodyMedium = TextStyle(
        fontFamily = pretendardFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = pretendardFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
    ),
)