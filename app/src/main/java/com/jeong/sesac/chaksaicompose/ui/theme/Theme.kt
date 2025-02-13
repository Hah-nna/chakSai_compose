package com.jeong.sesac.chaksaicompose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

const val lineHeightPercentage = 1.5f

private val DarkColorScheme = AppColorScheme(
    primary = YellowDark100,
    secondary = YellowDark200,
    tertiary = GreenDark100,
    outline = YellowDark600,
    outlineVariant = Yellow800,
    surface = YellowDark500,
    surfaceDim = Yellow200,
    error =Red100 ,
    onError = White,
    errorContainer = Red200,
    scrim = Black,
    primaryContainer = YellowDark300,
    secondaryContainer = YellowDark400,
    tertiaryContainer = GreenDark200

)

private val LightColorScheme = AppColorScheme(
    primary = Yellow300,
    secondary = Yellow400,
    tertiary = Green100,
    outline = Yellow700,
    outlineVariant = YellowDark700,
    surface = Yellow100,
    surfaceDim = YellowDark500,
    error = Red300,
    onError = BlackText,
    errorContainer = Red400,
    scrim = Black,
    primaryContainer = Yellow500,
    secondaryContainer =Yellow600,
    tertiaryContainer = Green200
)

private val typography = AppTypography(
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
        fontSize = 16.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = pretendardFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp,
    ),
)

private val shape = AppShape(
    container = RoundedCornerShape(12.dp),
    button = RoundedCornerShape(12.dp)
)

private val size = AppSize(
    xl = 32.dp,
    large = 24.dp,
    medium = 16.dp,
    normal = 12.dp,
    small = 8.dp,
    xs = 4.dp
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if(darkTheme) DarkColorScheme else LightColorScheme
    CompositionLocalProvider(
        LocalAppColorScheme provides colorScheme,
        LocalAppTypography provides typography,
        LocalAppShape provides shape,
        LocalAppSize provides size,
        content = content
    )

}

object AppTheme {
    val colorScheme: AppColorScheme
        @Composable get() = LocalAppColorScheme.current

    val typography: AppTypography
        @Composable get() = LocalAppTypography.current

    val shape: AppShape
        @Composable get() = LocalAppShape.current

    val size: AppSize
        @Composable get() = LocalAppSize.current
}

