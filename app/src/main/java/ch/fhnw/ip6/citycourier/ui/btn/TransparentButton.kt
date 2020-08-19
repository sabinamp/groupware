package ch.fhnw.ip6.citycourier.ui.btn

import androidx.compose.Composable

import androidx.ui.graphics.Color
import androidx.ui.material.Button
import androidx.ui.unit.Dp


@Composable
fun TransparentButton(text: String, onClick: (() -> Unit)? = null) {
    Button( onClick = {
            // handle button click
            },
            backgroundColor = Color.Transparent,
            contentColor = Color.DarkGray,
            elevation = Dp(7f)
    ){text}
}
