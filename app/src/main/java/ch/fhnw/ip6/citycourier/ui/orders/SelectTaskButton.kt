/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 */

package ch.fhnw.ip6.citycourier.ui.orders

import androidx.compose.Composable
import androidx.compose.key
import androidx.ui.core.Modifier
import androidx.ui.foundation.Icon
import androidx.ui.foundation.shape.corner.CircleShape
import androidx.ui.layout.padding
import androidx.ui.layout.preferredSize
import androidx.ui.material.EmphasisAmbient
import androidx.ui.material.ProvideEmphasis
import androidx.ui.material.Surface
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.Add
import androidx.ui.material.icons.filled.Done
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import ch.fhnw.ip6.citycourier.ui.ThemedPreview
import ch.fhnw.ip6.citycourier.ui.themes.LightThemeColors


@Composable
fun SelectTopicButton(
    modifier: Modifier = Modifier,
    selected: Boolean = false
) {
    key(selected) {
        val icon = if (selected) Icons.Filled.Done else Icons.Filled.Add
        val backgroundColor = if (selected) {
            LightThemeColors.primary
        } else {
            LightThemeColors.secondaryVariant.copy(alpha = 0.12f)
        }
        Surface(
            color = backgroundColor,
            shape = CircleShape,
            modifier = modifier.preferredSize(36.dp, 36.dp)
        ) {
            ProvideEmphasis(EmphasisAmbient.current.high) {
                Icon(icon)
            }
        }
    }
}

@Preview("Off")
@Composable
fun SelectTopicButtonPreviewOff() {
    SelectTopicButtonPreviewTemplate(
        darkTheme = false,
        selected = false
    )
}

@Preview("On")
@Composable
fun SelectTopicButtonPreviewOn() {
    SelectTopicButtonPreviewTemplate(
        darkTheme = false,
        selected = true
    )
}

@Preview("Off - dark theme")
@Composable
fun SelectTopicButtonPreviewOffDark() {
    SelectTopicButtonPreviewTemplate(
        darkTheme = true,
        selected = false
    )
}

@Preview("On - dark theme")
@Composable
fun SelectTopicButtonPreviewOnDark() {
    SelectTopicButtonPreviewTemplate(
        darkTheme = true,
        selected = true
    )
}

@Composable
private fun SelectTopicButtonPreviewTemplate(
    darkTheme: Boolean = false,
    selected: Boolean
) {
    ThemedPreview(darkTheme) {
        SelectTopicButton(
            modifier = Modifier.padding(32.dp),
            selected = selected
        )
    }
}
