package com.jeong.sesac.chaksaicompose.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
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

private val LightColorScheme = lightColorScheme(
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

@Composable
fun ChakSaiComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}