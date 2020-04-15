package ch.fhnw.ip6.citycourier.ui

import androidx.ui.core.sp
import androidx.ui.graphics.Color
import androidx.ui.material.Typography
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontWeight
import androidx.ui.text.font.Font
import androidx.ui.text.font.FontFamily


// regular = Font("Roboto-Regular")
val regular = Font(name="montserrat_regular.ttf")
val medium = Font(name="montserrat_medium.ttf", weight = FontWeight.W500)
val semibold = Font(name="montserrat_semibold.ttf", weight=FontWeight.W600)

val appFontFamily = FontFamily(fonts = listOf(regular, medium, semibold))


val themeTypography = Typography(
    h4 = TextStyle(
        fontFamily = appFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 30.sp
    ),
    h5 = TextStyle(
        fontFamily = appFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 24.sp,
        color = Color(27,97,160)
    ),
    h6 = TextStyle(
        fontFamily = appFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 20.sp,
        color = Color.White
    ),

    subtitle1 = TextStyle(
        fontFamily = appFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 16.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = appFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),

    body1 = TextStyle(
        fontFamily = appFontFamily,
        fontSize = 14.sp,
        color = Color.Black
    ),
    button = TextStyle(
        fontFamily = appFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = appFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    overline = TextStyle(
        fontFamily = appFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 12.sp
    )
)
