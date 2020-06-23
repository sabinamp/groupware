package ch.fhnw.ip6.citycourier.ui.btn

import androidx.compose.Composable
import androidx.ui.core.dp
import androidx.ui.foundation.shape.RectangleShape
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.*

import androidx.ui.material.Button
import androidx.ui.material.ButtonStyle import androidx.ui.tooling.preview.Preview
import ch.fhnw.ip6.citycourier.ui.themes.LightThemeColors

@Composable
fun EditButton(text: String, onClick: (() -> Unit)? = null) {
   /* Button( text = text, onClick = {},
        style = ButtonStyle(
            color = LightThemeColors.onPrimary,
            rippleColor = LightThemeColors.primaryVariant,
            elevation = 7.dp,
            shape = RectangleShape,
            paddings = EdgeInsets(5.dp)
        )
    )*/
    Button(text = text,
             style = ButtonStyle(LightThemeColors.onPrimary,
                 rippleColor = LightThemeColors.primaryVariant,
                 shape = RoundedCornerShape(8.dp)),
             modifier = MaxHeight(80.dp) wraps Width(150.dp),
             onClick = {})

}

@Preview
@Composable
fun EditButtonPreview(){
    EditButton(text = "Edit")
}