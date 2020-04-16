package ch.fhnw.ip6.citycourier.ui

import androidx.ui.core.sp
import androidx.ui.graphics.Color
import androidx.ui.material.Typography
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontWeight
import androidx.ui.text.font.Font
import androidx.ui.text.font.FontFamily



val regular = Font(name="montserrat_regular.otf")
val medium = Font(name="montserrat_black.otf", weight = FontWeight.W500)
val semibold = Font(name="montserrat_semibold.otf", weight = FontWeight.W600)

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
        fontWeight = FontWeight.W600,
        fontSize = 20.sp,
        color = Color.White
    ),

    subtitle1 = TextStyle(
        fontFamily = appFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 18.sp,
        color = Color.DarkGray
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
