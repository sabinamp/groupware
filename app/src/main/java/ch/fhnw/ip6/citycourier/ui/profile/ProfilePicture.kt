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

import ch.fhnw.ip6.citycourier.ui.themes.themeTypography


@Composable
fun ProfilePicture(name:String){

    Box(modifier= Modifier.height(250.dp),
            shape = RoundedCornerShape(topLeft = 90.dp, topRight = 90.dp,
            bottomLeft = 0.dp, bottomRight = 0.dp)
    ){

        Column(modifier = Modifier.padding(10.dp)) {
            Box(modifier = Modifier.padding(5.dp).plus(Modifier.width(80.dp))) {
                Image(vectorResource(R.drawable.ic_profile_80))
            }
            Box(padding = 5.dp) {
                Text(name, style = themeTypography.h2, maxLines = 1)
            }
            Box(padding = 5.dp) {
                Row(modifier = Modifier.height(50.dp)){
                    Text("Available", style = themeTypography.h6, maxLines = 1)
                    Spacer(modifier = Modifier.width(5.dp))

                }

            }

                }



        }

    }



@Preview
@Composable
fun ProfilePicturePreview(){
    ProfilePicture("Martin Hauser")
}