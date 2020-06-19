package ch.fhnw.ip6.citycourier.ui.btn

import android.graphics.drawable.shapes.Shape
import androidx.compose.Composable
import androidx.ui.core.Dp
import androidx.ui.graphics.Color
import androidx.ui.material.Button
import androidx.ui.material.ContainedButtonStyle
import ch.fhnw.ip6.citycourier.ui.LightThemeColors

@Composable
fun TransparentButton(text: String, onClick: (() -> Unit)? = null) {
    Button(
        text = text, onClick = {
            // handle button click
        }, style = ContainedButtonStyle(
            color = Color.Transparent,
            rippleColor = LightThemeColors.secondary,
            elevation = Dp(7f)
        )
    )
}