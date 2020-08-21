package ch.fhnw.ip6.citycourier.ui.themes


import androidx.ui.graphics.Color
import androidx.ui.material.Typography
import androidx.ui.text.TextStyle
import androidx.ui.text.font.*
import androidx.ui.unit.sp
import ch.fhnw.ip6.citycourier.R


val regular = font(R.font.montserrat_regular)
val medium = font(R.font.montserrat_medium, FontWeight.W500)
val semibold = font(R.font.montserrat_semibold, FontWeight.W600)

val appFontFamily = fontFamily(
    fonts = listOf(
        regular,
        medium,
        semibold
    )
)

private val bodyFontFamily = fontFamily(
    fonts = listOf(
        font(R.font.domine_regular),
        font(R.font.domine_bold, FontWeight.Bold)
    )
)

val themeTypography = Typography(
    h2 = TextStyle(
        fontFamily = appFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 24.sp,
        color = LightThemeColors.secondary
    ),
    h3 = TextStyle(
        fontFamily = appFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 20.sp,
        color = Color.White
    ),
    h4 = TextStyle(
        fontFamily = appFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 19.sp,
        color = LightThemeColors.secondary
    ),
    h5 = TextStyle(
        fontFamily = appFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 20.sp,
        color = Color.Black

    ),
    h6 = TextStyle(
        fontFamily = appFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 18.sp,
        color = Color(29,167,155)
    ),

    subtitle1 = TextStyle(
        fontFamily = appFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 18.sp,
        color = LightThemeColors.onBackground
       // color = Color(229,177,151)
    ),
    subtitle2 = TextStyle(
        fontFamily = appFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp,
        color = Color(29,167,155)

    ),

    body1 = TextStyle(
        fontFamily = appFontFamily,
        fontSize = 16.sp,
        color = LightThemeColors.onSecondary,
    ),
    body2 = TextStyle(
        fontFamily = appFontFamily,
        fontSize = 14.sp,
        color = LightThemeColors.onPrimary,
        fontStyle = FontStyle.Normal
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
        fontSize = 12.sp,
        fontStyle = FontStyle.Italic
    )
)
