package ch.fhnw.ip6.citycourier.ui.profile

import androidx.compose.Composable
import androidx.compose.remember

import androidx.ui.core.*
import androidx.ui.foundation.Box
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.tooling.preview.Preview
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.res.vectorResource

import androidx.ui.unit.dp
import ch.fhnw.ip6.citycourier.ui.themes.LightThemeColors

import ch.fhnw.ip6.citycourier.R
import ch.fhnw.ip6.citycourier.data.CourierRepository
import ch.fhnw.ip6.citycourier.ui.AppDrawer
import ch.fhnw.ip6.citycourier.ui.Screen

import ch.fhnw.ip6.citycourier.ui.ThemedPreview
import ch.fhnw.ip6.citycourier.ui.btn.EditButton
import ch.fhnw.ip6.citycourier.ui.orders.TasksScreenBody
import ch.fhnw.ip6.citycourier.ui.themes.CityCourierTheme

@Composable
fun ProfileScreenBody(courierId: String) {

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
fun ProfileScreen(courierId: String, scaffoldState: ScaffoldState = remember { ScaffoldState() },
/*courierRepository: CourierRepository*/){
    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            AppDrawer(
                currentScreen = Screen.TasksScreen,
                closeDrawer = { scaffoldState.drawerState = DrawerState.Closed }
            )
        },
        topAppBar = {
            TopAppBar(
                title = { Text("Your Profile") },
                navigationIcon = {
                    IconButton(onClick = { scaffoldState.drawerState = DrawerState.Opened }) {
                        Icon(vectorResource(R.drawable.ic_menu_icon_24))
                    }
                }
            )
        },
        bodyContent = {

            ProfileScreenBody(courierId = courierId)
        }
    )
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
        ProfileInfo(courierId = "C000")
    }
}

@Preview("Profile Screen Body Preview")
@Composable
fun ProfileScreenBodyPreview(){
    ThemedPreview(darkTheme = false) {
        ProfileScreenBody(courierId = "C000")
    }
}