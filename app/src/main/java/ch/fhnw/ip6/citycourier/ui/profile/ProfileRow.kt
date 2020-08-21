package ch.fhnw.ip6.citycourier.ui.profile

import androidx.compose.Composable

import androidx.ui.core.Modifier
import androidx.ui.core.clip

import androidx.ui.foundation.Box
import androidx.ui.foundation.Image
import androidx.ui.foundation.Text

import androidx.ui.graphics.Color

import androidx.ui.layout.*
import androidx.ui.material.Divider
import androidx.ui.material.MaterialTheme
import androidx.ui.res.vectorResource
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import ch.fhnw.ip6.citycourier.R

import ch.fhnw.ip6.citycourier.ui.themes.themeTypography

@Composable
fun ProfileRow(title:String, value:String, imageUrl:Int){
    Row(modifier = Modifier.padding(8.dp)){
        Box(padding = 10.dp){
              Image(asset= vectorResource(imageUrl),
                  modifier = Modifier
                  .preferredSize(24.dp, 24.dp)
                  .clip(MaterialTheme.shapes.small))
        }
        Box(padding = 10.dp) {

                Text(text = title, style = themeTypography.subtitle2)


        }
        Box(padding = 10.dp) {
         Text(text = value, style = themeTypography.subtitle2)

        }
    }
    Divider(color = Color.DarkGray)
}

@Preview
@Composable
fun ProfileRowPreview(){
    ProfileRow(title = "Name", value = "Marina Hauser", imageUrl = R.drawable.ic_profile_60)
}