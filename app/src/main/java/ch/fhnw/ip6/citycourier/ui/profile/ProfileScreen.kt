package ch.fhnw.ip6.citycourier.ui.profile

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.*
import androidx.ui.foundation.DrawImage
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.*

import androidx.ui.material.MaterialTheme
import androidx.ui.material.surface.Card
import androidx.ui.material.surface.Surface
import androidx.ui.res.imageResource

import androidx.ui.tooling.preview.Preview
import ch.fhnw.ip6.citycourier.ui.themes.LightThemeColors
import ch.fhnw.ip6.citycourier.ui.themes.themeTypography

import ch.fhnw.ip6.citycourier.R
import ch.fhnw.ip6.citycourier.ui.btn.EditButton

@Composable
fun ProfileScreen(courierId: String){

    MaterialTheme (colors = LightThemeColors, typography = themeTypography) {
        Column(
            modifier = Spacing(5.dp)
        ) {
            NavBack()
            HeightSpacer(5.dp)
            ProfilePicture("John Smith")
            HeightSpacer(height = 5.dp)
           ProfileInfo(courierId = courierId)
        }
    }
}




@Composable
fun NavBack() {
      Padding(padding = 10.dp) {
                   Container(
                        width = Dp(60f),
                        height = Dp(60f),
                        alignment = Alignment.Center
                    ) {
                        Clip(shape = RoundedCornerShape(8.dp)) {
                            DrawImage(image = +imageResource(R.drawable.arrow))
                        }
                    }

            }

}




@Composable
fun ProfileInfo(courierId: String) {
    Card(
        modifier = MinHeight(300.dp) wraps Expanded,
        color = LightThemeColors.onBackground, elevation = 7.dp
    ) {
        Column {
           ProfileRow(title = "ID",
               value = "C100",
               imageUrl = R.drawable.ic_face_id_24)

             ProfileRow(
                title = "Name",
                value = "Martin Hauser",
                imageUrl = R.drawable.ic_user_24
            )

             ProfileRow(
                 title = "Region",
                 value = "Zürich West",
                 imageUrl = R.drawable.ic_tracking_24
             )

             ProfileRow(
                 title = "Email",
                 value = "info@citycourier.com",
                 imageUrl = R.drawable.ic_mail_24
             )
             ProfileRow(
                 title = "Phone",
                 value = "41442205020",
                 imageUrl = R.drawable.ic_phone_24
             )
            FlexRow(){
                expanded(flex=1.0f){
                    Padding(padding = 30.dp) {
                        EditButton(text = "Edit", onClick = {})

                    }
                }

            }


        }

    }
}
    

@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen("C100")

}