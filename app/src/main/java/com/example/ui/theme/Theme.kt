package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color.White,
    onPrimary = NytBlack,
    primaryContainer = NytDarkSurface,
    onPrimaryContainer = Color.White,
    secondary = NytSubscribeBlue,
    onSecondary = Color.White,
    background = NytDarkBackground,
    onBackground = NytDarkText,
    surface = NytDarkSurface,
    onSurface = NytDarkText,
    surfaceVariant = Color(0xFF242424),
    onSurfaceVariant = NytDarkTextMuted,
    outline = NytDarkBorder
)

private val LightColorScheme = lightColorScheme(
    primary = NytBlack,
    onPrimary = Color.White,
    primaryContainer = NytCardBg,
    onPrimaryContainer = NytBlack,
    secondary = NytSubscribeBlue,
    onSecondary = Color.White,
    background = NytBackground,
    onBackground = NytBlack,
    surface = NytBackground,
    onSurface = NytBlack,
    surfaceVariant = NytCardBg,
    onSurfaceVariant = NytDarkGray,
    outline = NytBorder
)

@Composable
fun NYTimesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
