package ch.fhnw.ip6.citycourier.ui.btn

import androidx.compose.Composable
import androidx.ui.foundation.Border
import androidx.ui.foundation.Text
import androidx.ui.foundation.shape.corner.CircleShape

import androidx.ui.graphics.Color
import androidx.ui.graphics.RectangleShape
import androidx.ui.material.Button
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.Dp
import androidx.ui.unit.dp
import ch.fhnw.ip6.citycourier.ui.ThemedPreview


@Composable
fun TransparentButton(text: String, onClick: (() -> Unit)? = null) {
    Button(
        onClick = {
            // handle button click
        },
        shape = RectangleShape,
        backgroundColor = Color.Transparent,
        contentColor = Color.Black,
        elevation = Dp(7f),
        border = Border(1.dp, color = Color.DarkGray),
        text = { Text(text = text, color = Color.Black) }
    )
}

@Preview
@Composable
fun TransparentButtonPreview(){
    ThemedPreview() {
        TransparentButton(text = "Hello")
    }
}