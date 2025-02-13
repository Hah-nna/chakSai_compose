package com.jeong.sesac.chaksaicompose.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

data class AppColorScheme(
    val primary: Color,
    val secondary: Color,
    val tertiary: Color,
    val outline: Color,
    val outlineVariant: Color,
    val surface: Color,
    val surfaceDim: Color,
    val error: Color,
    val onError: Color,
    val errorContainer: Color,
    val scrim: Color,
    val primaryContainer: Color,
    val secondaryContainer: Color,
    val tertiaryContainer: Color
)

data class AppTypography(
    val titleLarge: TextStyle,
    val titleMedium: TextStyle,
    val titleSmall: TextStyle,
    val labelLarge: TextStyle,
    val labelMedium: TextStyle,
    val labelSmall: TextStyle,
    val bodyLarge: TextStyle,
    val bodyMedium: TextStyle,
    val bodySmall: TextStyle,
)

data class AppShape(
    val container: Shape,
    val button: Shape,
)

data class AppSize(
    val large: Dp,
    val medium: Dp,
    val normal: Dp,
    val small: Dp
)

val LocalAppColorScheme = staticCompositionLocalOf {
    AppColorScheme(
        primary = Color.Unspecified,
        secondary = Color.Unspecified,
        tertiary = Color.Unspecified,
        outline = Color.Unspecified,
        outlineVariant = Color.Unspecified,
        surface = Color.Unspecified,
        surfaceDim = Color.Unspecified,
        error = Color.Unspecified,
        onError = Color.Unspecified,
        errorContainer = Color.Unspecified,
        scrim = Color.Unspecified,
        primaryContainer = Color.Unspecified,
        secondaryContainer = Color.Unspecified,
        tertiaryContainer = Color.Unspecified
    )
}

val LocalAppTypography = staticCompositionLocalOf {
    AppTypography(
        titleLarge = TextStyle.Default,
        titleMedium = TextStyle.Default,
        titleSmall = TextStyle.Default,
        labelLarge = TextStyle.Default,
        labelMedium = TextStyle.Default,
        labelSmall = TextStyle.Default,
        bodyLarge = TextStyle.Default,
        bodyMedium = TextStyle.Default,
        bodySmall = TextStyle.Default,
        )
}

val LocalAppShape = staticCompositionLocalOf {
    AppShape(
        container = RectangleShape,
        button = RectangleShape
    )
}

val LocalAppSize = staticCompositionLocalOf {
    AppSize(
        large = Dp.Unspecified,
        medium = Dp.Unspecified,
        normal = Dp.Unspecified,
        small = Dp.Unspecified
    )
}