package ch.fhnw.ip6.citycourier.ui.themes

import androidx.compose.Composable
import androidx.compose.Effect
import androidx.ui.foundation.isSystemInDarkTheme
import androidx.ui.material.MaterialTheme

@Composable
fun CityCourierTheme(darkTheme: Effect<Boolean> = isSystemInDarkTheme(),
                     content: @Composable() () -> Unit) {
    MaterialTheme(
        colors = LightThemeColors,
        typography = themeTypography,
        children = content
    )
}