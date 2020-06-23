package ch.fhnw.ip6.citycourier.ui.profile

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.graphics.vector.DrawVector
import androidx.ui.layout.Container
import androidx.ui.layout.FlexRow
import androidx.ui.layout.Padding
import androidx.ui.layout.Spacing
import androidx.ui.material.Divider
import androidx.ui.res.vectorResource
import androidx.ui.tooling.preview.Preview
import ch.fhnw.ip6.citycourier.R
import ch.fhnw.ip6.citycourier.ui.themes.LightThemeColors
import ch.fhnw.ip6.citycourier.ui.themes.themeTypography

@Composable
fun ProfileRow(title:String, value:String, imageUrl:Int){
    FlexRow(modifier = Spacing(8.dp)){
        expanded(flex=1.0f){
            Padding(padding = 5.dp) {
                Container(height = 24.dp, width = 24.dp) {
                    DrawVector(vectorImage = +vectorResource(imageUrl))
                }
            }
        }
        expanded(flex = 2.0f) {
            Padding(padding = 5.dp) {
                Text(text = title, style = themeTypography.subtitle2)
            }

        }
        expanded(flex = 4.0f) {
            Padding(padding = 5.dp) {
                Text(text = value, style = themeTypography.subtitle2)
            }

        }
    }
    Divider(color = LightThemeColors.primaryVariant)
}

@Preview
@Composable
fun ProfileRowPreview(){
    ProfileRow(title = "Name", value = "Marina Hauser", imageUrl = R.drawable.ic_bell_24)
}