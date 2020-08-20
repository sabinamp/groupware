package ch.fhnw.ip6.citycourier.ui.profile

import androidx.compose.Composable

import androidx.ui.core.*
import androidx.ui.foundation.Box
import androidx.ui.tooling.preview.Preview
import androidx.ui.layout.*
import androidx.ui.material.Card

import androidx.ui.unit.dp
import ch.fhnw.ip6.citycourier.ui.themes.LightThemeColors

import ch.fhnw.ip6.citycourier.R
import ch.fhnw.ip6.citycourier.data.CourierRepository

import ch.fhnw.ip6.citycourier.ui.ThemedPreview
import ch.fhnw.ip6.citycourier.ui.btn.EditButton
import ch.fhnw.ip6.citycourier.ui.themes.CityCourierTheme

@Composable
fun ProfileScreen(courierId: String, courierRepository: CourierRepository) {

    CityCourierTheme {
        Column(
            modifier = Modifier.padding(5.dp)
        ) {
            //NavIcon
            Spacer(modifier = Modifier.height(5.dp))
            ProfilePicture("John Smith")
            Spacer(modifier = Modifier.height(5.dp))
            ProfileInfo(courierId = courierId)
        }
    }
}
   


@Composable
fun ProfileInfo(courierId: String) {
    Card(
        modifier = Modifier.height(300.dp).plus(Modifier.fillMaxWidth()),
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
            Row(){

                    Box(padding = 30.dp) {
                        EditButton(onClick = {})

                    }


            }


        }

    }
}

@Preview("Profile Info Preview")
@Composable
fun ProfileInfoPreview(){
    ThemedPreview(darkTheme = false) {
        ProfileInfo(courierId = "C100")
    }
}
