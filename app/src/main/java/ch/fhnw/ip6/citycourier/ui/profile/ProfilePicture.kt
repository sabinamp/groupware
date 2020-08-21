package ch.fhnw.ip6.citycourier.ui.profile

import androidx.compose.Composable

import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.Image
import androidx.ui.foundation.Text

import androidx.ui.foundation.shape.corner.CircleShape
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color

import androidx.ui.layout.*

import androidx.ui.res.imageResource

import androidx.ui.res.vectorResource
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import ch.fhnw.ip6.citycourier.R
import ch.fhnw.ip6.citycourier.model.Conn
import ch.fhnw.ip6.citycourier.model.CourierStatus
import ch.fhnw.ip6.citycourier.ui.themes.LightThemeColors

import ch.fhnw.ip6.citycourier.ui.themes.themeTypography


@Composable
fun ProfilePicture(name:String, available: CourierStatus){

    Box(modifier= Modifier.height(260.dp).plus(Modifier.fillMaxWidth()),
        backgroundColor = LightThemeColors.onBackground,
            shape = RoundedCornerShape(topLeft = 90.dp, topRight = 90.dp,
            bottomLeft = 0.dp, bottomRight = 0.dp)
    ){

        Column(modifier = Modifier.padding(25.dp), horizontalGravity = Alignment.CenterHorizontally) {
            Box(modifier = Modifier.padding(5.dp).plus(Modifier.width(80.dp))) {
                Image(vectorResource(R.drawable.ic_profile_80))
            }
            Box(padding = 10.dp) {
                Column(modifier = Modifier.height(55.dp)){
                Text(name, style = themeTypography.h2, maxLines = 1, modifier = Modifier.padding(4.dp))

                Text(available.toString(), style = themeTypography.h6, maxLines = 1,modifier = Modifier.padding(4.dp))
                    Spacer(modifier = Modifier.width(5.dp))
                }
            }


        }


    }

    }



@Preview
@Composable
fun ProfilePicturePreview(){
    ProfilePicture("Martin Hauser", CourierStatus.AVAILABLE)
}