package ch.fhnw.ip6.citycourier.ui.welcome


import androidx.compose.*
import androidx.ui.core.*
import androidx.ui.foundation.Box

import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text

import androidx.ui.layout.*
import androidx.ui.material.*

import androidx.ui.res.vectorResource
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import ch.fhnw.ip6.citycourier.R
import ch.fhnw.ip6.citycourier.data.FakeTaskRequestsRepository
import ch.fhnw.ip6.citycourier.data.TaskRequestsRepository
import ch.fhnw.ip6.citycourier.model.TaskRequest
import ch.fhnw.ip6.citycourier.state.previewDataFrom
import ch.fhnw.ip6.citycourier.ui.AppDrawer
import ch.fhnw.ip6.citycourier.ui.Screen
import ch.fhnw.ip6.citycourier.ui.ThemedPreview
import ch.fhnw.ip6.citycourier.ui.themes.CityCourierTheme


@Composable
fun WelcomeScreen(
    taskRequestRepository: TaskRequestsRepository,
    scaffoldState: ScaffoldState = remember { ScaffoldState() }
) {
    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            AppDrawer(
                currentScreen = Screen.WelcomeScreen,
                closeDrawer = { scaffoldState.drawerState = DrawerState.Closed }
            )
        },
        topAppBar = {
            TopAppBar(
                title = { Text(text = "City Courier") },
                navigationIcon = {
                    IconButton(onClick = { scaffoldState.drawerState = DrawerState.Opened }) {
                        Icon(vectorResource(R.drawable.ic_menu_icon_24))
                    }
                }
            )
        },
        bodyContent = { modifier ->
            WelcomeScreenContent(taskRequestRepository, modifier)
        }
    )
}


@Composable
 fun WelcomeScreenContent(taskRequestRepository: Any?, modifier: Modifier) {


   CityCourierTheme{

       Box(padding = 5.dp ) {

                NotifyListBody()
       }
    }
}



@Composable
fun NotifyListBody(){
    Box(padding = 8.dp){
        Column{
            AlertCard()
        }
    }

}






@Preview("Home screen, open drawer dark theme")
@Composable
private fun PreviewDrawerOpenDark() {
    ThemedPreview(darkTheme = true) {
        WelcomeScreen(
            taskRequestRepository = FakeTaskRequestsRepository(ContextAmbient.current),
            scaffoldState = ScaffoldState(drawerState = DrawerState.Opened)
        )
    }
}

@Composable
private fun loadFakePosts(): List<TaskRequest> {
    return previewDataFrom(FakeTaskRequestsRepository(ContextAmbient.current)::getTaskRequests)
}
