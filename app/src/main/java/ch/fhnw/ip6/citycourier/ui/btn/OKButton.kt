package ch.fhnw.ip6.citycourier.ui.btn

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Image
import androidx.ui.foundation.drawBackground
import androidx.ui.foundation.shape.corner.CircleShape

import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.graphics.drawscope.Stroke
import androidx.ui.layout.fillMaxSize
import androidx.ui.layout.height
import androidx.ui.layout.padding
import androidx.ui.layout.width

import androidx.ui.material.Button
import androidx.ui.material.IconButton
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.Bookmark
import androidx.ui.res.imageResource
import androidx.ui.res.vectorResource

import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.Dp
import androidx.ui.unit.dp
import ch.fhnw.ip6.citycourier.R
import ch.fhnw.ip6.citycourier.ui.ThemedPreview
import ch.fhnw.ip6.citycourier.ui.themes.LightThemeColors


@Composable
fun OKButton(onClick: (() -> Unit)? = null) {
    val editIcon=
    Button(onClick = {}, shape = CircleShape, backgroundColor = LightThemeColors.secondaryVariant) {
        Icon(asset = imageResource(R.drawable.ok),modifier = Modifier.drawBackground(
            color = Color.White,
            style = Stroke(4f),
            shape = CircleShape
        ).padding(5.dp))
    }

}

@Preview
@Composable
fun OKButtonPreview(){
    ThemedPreview{
        OKButton()
    }

}