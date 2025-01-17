package ch.fhnw.ip6.citycourier.ui.profile

import androidx.compose.*

import androidx.ui.core.*
import androidx.ui.foundation.Box
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.graphics.Color
import androidx.ui.tooling.preview.Preview
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.res.vectorResource

import androidx.ui.unit.dp
import ch.fhnw.ip6.citycourier.ui.themes.LightThemeColors

import ch.fhnw.ip6.citycourier.R
import ch.fhnw.ip6.citycourier.data.*
import ch.fhnw.ip6.citycourier.model.*
import ch.fhnw.ip6.citycourier.state.UiState
import ch.fhnw.ip6.citycourier.ui.AppDrawer
import ch.fhnw.ip6.citycourier.ui.Screen

import ch.fhnw.ip6.citycourier.ui.ThemedPreview
import ch.fhnw.ip6.citycourier.ui.btn.EditButton
import ch.fhnw.ip6.citycourier.ui.effects.fetchCourierInfo
import ch.fhnw.ip6.citycourier.ui.themes.CityCourierTheme



@Composable
fun ProfileScreen(scaffoldState: ScaffoldState = remember { ScaffoldState() },
                  courierRepository: CourierRepository) {
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
          bodyContent = {modifier ->
              ProfileScreenContent(courierRepository = courierRepository, modifier = modifier)

          }
      )

}

@Composable
private fun ProfileScreenContent(courierRepository: CourierRepository, modifier: Modifier) {

    val courierDataState = fetchCourierInfo(courierId = CURRENT_COURIER_ID, courierRepository = courierRepository)

    if (courierDataState is UiState.Success<CourierInfo>) {
        ProfileScreenBody(courierId = CURRENT_COURIER_ID, courierInfo = courierDataState.data, modifier = modifier)
    }
}


@Composable
private fun ProfileScreenBody(courierId: String, courierInfo: CourierInfo,
                              modifier: Modifier = Modifier) {
    CityCourierTheme {
        Column(
            modifier = Modifier.padding(5.dp)
        ) {
            //NavIcon
            Spacer(modifier = Modifier.height(5.dp))
            ProfilePicture(courierInfo.courierName, courierInfo.status)
            Spacer(modifier = Modifier.height(5.dp))
            ProfileInfo(courierId = courierId, courierInfo = courierInfo)
        }
    }
}

@Composable
fun ProfileInfo(courierId: String, courierInfo:CourierInfo) {
    Card(
        modifier = Modifier.height(430.dp).plus(Modifier.fillMaxWidth()),
        color = MaterialTheme.colors.onBackground, elevation = 7.dp
    ) {
        Column {
           ProfileRow(title = "ID",
               value = courierId,
               imageUrl = R.drawable.ic_face_id_24_black)

             ProfileRow(
                title = "Name",
                value = courierInfo.courierName,
                imageUrl = R.drawable.ic_user_24_black
            )

             ProfileRow(
                 title = "Assigned Tasks",
                 value = courierInfo.assignedOrders.toString(),
                 imageUrl = R.drawable.ic_tracking_24_black
             )

             ProfileRow(
                 title = "Email",
                 value = courierInfo.contactInfo.email.email.toString(),
                 imageUrl = R.drawable.ic_mail_24_black
             )
             ProfileRow(
                 title = "Phone Number",
                 value = courierInfo.contactInfo.phones[0].phoneNumber,
                 imageUrl = R.drawable.ic_phone_24_black
             )
            ProfileRow(
                title = "Phone Connected",
                value = courierInfo.conn.toString(),
                imageUrl = R.drawable.ic_phone_24_black
            )
            ProfileRow(
                title = "Completed Tasks",
                value = "OR1000",
                imageUrl = R.drawable.ic_tracking_24_black
            )


        }

    }
}

@Composable
private fun loadFakeCourier(courierId: String): CourierInfo {
    var infoC: CourierInfo? = null
    BlockingFakeCourierRepository(ContextAmbient.current).getCourier(courierId) { result ->
        infoC = result.successOr(null)
    }
    return infoC!!
}

@Preview("Profile Info Preview")
@Composable
fun ProfileInfoPreview(){
    ThemedPreview(darkTheme = false) {
        ProfileInfo(courierId = "C106", courierInfo = loadFakeCourier("C106"))
    }
}



