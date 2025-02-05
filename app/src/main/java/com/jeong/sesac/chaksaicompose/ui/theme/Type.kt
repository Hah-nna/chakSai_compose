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
    Font(R.font.pretendard_regular, FontWeight.Normal),
    Font(R.font.pretendard_semi_bold, FontWeight.SemiBold),
    Font(R.font.pretendard_bold, FontWeight.Bold),
    Font(R.font.pretendard_extra_bold, FontWeight.ExtraBold)
)

val AppTypography = Typography(
    titleLarge = TextStyle(
        fontFamily = pretendardFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 22.sp,
        lineHeight = 24.sp * lineHeightPercentage
    ),
    titleMedium = TextStyle(
        fontFamily = pretendardFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 18.sp,
        lineHeight = 18.sp * lineHeightPercentage
    ),
    titleSmall = TextStyle(
        fontFamily = pretendardFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 14.sp,
        lineHeight = 14.sp * lineHeightPercentage
    ),
    // button
    labelLarge = TextStyle(
        fontFamily = pretendardFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 18.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = pretendardFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = pretendardFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
    ),
    // body
    bodyLarge = TextStyle(
        fontFamily = pretendardFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 18.sp,
        lineHeight = 18.sp * lineHeightPercentage
    ),
    bodyMedium = TextStyle(
        fontFamily = pretendardFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = pretendardFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 10.sp,
    ),
)