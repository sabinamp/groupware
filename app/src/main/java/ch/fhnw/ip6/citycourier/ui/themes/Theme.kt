/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
