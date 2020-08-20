package ch.fhnw.ip6.citycourier.ui.themes

import androidx.compose.Composable
import androidx.ui.foundation.isSystemInDarkTheme
import androidx.ui.graphics.Color
import androidx.ui.material.ColorPalette
import androidx.ui.material.MaterialTheme

import androidx.ui.material.darkColorPalette
import androidx.ui.material.lightColorPalette

val LightThemeColors = lightColorPalette(
    primary = primaryBlue,
    primaryVariant = primaryVariantBlue,
    onPrimary = Color.White,
    secondary = secondaryBlue,
    onSecondary = onSecondaryWhite,
    error = errorRed,
    onError = onErrorPink,
    background = backgroundGray
)

private val DarkThemeColors = darkColorPalette(
    primary = Red300,
    primaryVariant = Red700,
    onPrimary = Color.Black,
    secondary = Red300,
    onSecondary = Color.White,
    error = Red200,
    onError = Red700,
    background = background2
)

@Composable
val ColorPalette.snackbarAction: Color
    get() = if (isLight) primaryBlue else secondaryBlue

@Composable
fun CityCourierTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    MaterialTheme(
        colors = if (darkTheme) DarkThemeColors else LightThemeColors,
        typography = themeTypography,
        shapes = shapes,
        content = content
    )
}
