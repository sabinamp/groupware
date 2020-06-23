package ch.fhnw.ip6.citycourier.ui.welcome


import androidx.compose.Composable
import androidx.compose.ambient
import androidx.compose.unaryPlus
import androidx.ui.core.*
import androidx.ui.foundation.DrawImage
import androidx.ui.foundation.shape.corner.CircleShape

import androidx.ui.foundation.shape.corner.RoundedCornerShape

import androidx.ui.graphics.vector.DrawVector
import androidx.ui.layout.*

import androidx.ui.material.MaterialTheme
import androidx.ui.res.imageResource
import androidx.ui.res.vectorResource
import androidx.ui.tooling.preview.Preview
import ch.fhnw.ip6.citycourier.R
import ch.fhnw.ip6.citycourier.data.AppContainer
import ch.fhnw.ip6.citycourier.data.TaskRequestsRepository
import ch.fhnw.ip6.citycourier.ui.themes.LightThemeColors
import ch.fhnw.ip6.citycourier.ui.themes.themeTypography


@Composable
fun CityCourierLogo() {
    Container(modifier = Height(100.dp) wraps Expanded) {
        Clip(shape = RoundedCornerShape(8.dp)) {
            DrawImage(image = +imageResource(R.drawable.deliveryservice_logo))
        }
    }
}

@Composable
fun CityCourierApp(appContainer: AppContainer) {
    MaterialTheme (colors = LightThemeColors, typography = themeTypography) {
        AppContent(taskRequestsRepository = appContainer.taskRequestRepository )
    }
}

@Composable
 fun WelcomeScreen() {
    val context = +ambient(ContextAmbient)

    MaterialTheme (colors = LightThemeColors, typography = themeTypography) {

            Column( modifier = Spacing(5.dp)) {
                CityCourierLogo()
                HeightSpacer(4.dp)
                Row(modifier = Spacing(2.dp), arrangement = Arrangement.Center ) {
                    IconsOverview()
                }
                HeightSpacer(4.dp)
                NotifyListBody()
                }
    }
}


@Composable
fun NotifyListBody(){
    Padding(padding = 8.dp){
        Column{
            AlertCard()
        }
    }

}

@Composable
fun IconsOverview() {
    MaterialTheme (colors = LightThemeColors, typography = themeTypography) {
        FlexRow(modifier = Spacing(14.dp)) {
            expanded(flex=1.0f) {
                Column(){
                    Container(
                        width = Dp(60f),
                        height = Dp(60f),
                        alignment = Alignment.Center,
                        padding = EdgeInsets(10.dp)
                    ) {
                        Clip(shape = CircleShape) {
                            DrawVector(vectorImage = +vectorResource(R.drawable.ic_bell_60))
                        }

                    }
                    Text("Orders", style = themeTypography.subtitle1)
                }
            }

            WidthSpacer(width = 18.dp)

            expanded(flex=1.0f){
                Column(){
                    Container(
                        width = Dp(60f),
                        height = Dp(60f),
                        alignment = Alignment.Center
                    ) {
                        Clip(shape = CircleShape) {
                            DrawVector(vectorImage = +vectorResource(R.drawable.ic_user_60))
                        }

                    }
                    Text("Profile" , style= themeTypography.subtitle1 )

                }
            }

            WidthSpacer(width = 14.dp)

            expanded(flex=1.0f){
               Column(){
                   Container(
                       width = Dp(60f),
                       height = Dp(60f),
                       alignment = Alignment.Center
                   ) {
                       Clip(shape = CircleShape) {
                           DrawVector(vectorImage = +vectorResource(R.drawable.ic_team_60))
                       }
                    }
                   Text("Team", style= themeTypography.subtitle1)
               }
           }
        }
    }
}


@Composable
private fun AppContent(taskRequestsRepository: TaskRequestsRepository) {
/*    Crossfade(CityCourierStatus.currentScreen) { screen ->
        Surface(color = LightThemeColors.background) {
            when (screen) {
                is Screen.Home -> WelcomeScreen(taskRequestsRepository = taskRequestsRepository)
                is Screen.Orders -> OrdersScreen(*//*ordersRepository = ordersRepository*//*)
                is Screen.Profile -> ProfileScreen(
                    *//*courierId = screen.courierId,
                    courierRepository = courierRepository*//*
                )
            }
        }
    }*/
}

@Preview
@Composable
fun DefaultPreview() {
    WelcomeScreen()

}
