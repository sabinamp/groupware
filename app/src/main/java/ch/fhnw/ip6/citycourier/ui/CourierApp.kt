package ch.fhnw.ip6.citycourier.ui

import androidx.compose.Composable
import androidx.ui.animation.Crossfade
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.foundation.Image
import androidx.ui.foundation.Text
import androidx.ui.graphics.ColorFilter
import androidx.ui.graphics.vector.VectorAsset
import androidx.ui.layout.*
import androidx.ui.material.Divider
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Surface
import androidx.ui.material.TextButton
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.Home
import androidx.ui.material.icons.filled.ListAlt
import androidx.ui.res.vectorResource
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp

import ch.fhnw.ip6.citycourier.R
import ch.fhnw.ip6.citycourier.data.AppContainer
import ch.fhnw.ip6.citycourier.data.CourierRepository
import ch.fhnw.ip6.citycourier.data.OrdersRepository
import ch.fhnw.ip6.citycourier.data.TaskRequestsRepository
import ch.fhnw.ip6.citycourier.ui.orders.OrdersScreen
import ch.fhnw.ip6.citycourier.ui.orders.TaskDetailsScreen
import ch.fhnw.ip6.citycourier.ui.profile.ProfileScreen
import ch.fhnw.ip6.citycourier.ui.themes.CityCourierTheme

import ch.fhnw.ip6.citycourier.ui.welcome.WelcomeScreen


@Composable
fun CityCourierLogo(modifier: Modifier = Modifier) {
     Row(modifier = modifier) {
         Image(
             asset = vectorResource(R.drawable.ic_menu_icon_24),
             colorFilter = ColorFilter.tint(MaterialTheme.colors.primary)
         )
         Spacer(Modifier.preferredWidth(8.dp))
     }
}


@Composable
fun CityCourierApp(appContainer: AppContainer) {
    CityCourierTheme {
        AppContent(
            taskRequestRepository = appContainer.taskRequestRepository,
            orderRepository = appContainer.ordersRepository,
            courierRepository = appContainer.courierRepository
        )
    }
}

@Composable
private fun AppContent(
    taskRequestRepository: TaskRequestsRepository,
    orderRepository: OrdersRepository,
    courierRepository: CourierRepository
    ) {  Crossfade(current = CityCourierStatus.currentScreen) { screen ->
         Surface(color = MaterialTheme.colors.background) {
            when (screen) {
                is Screen.WelcomeScreen -> WelcomeScreen(taskRequestRepository)
                is Screen.OrdersScreen -> OrdersScreen(orderRepository)
                is Screen.ProfileScreen -> ProfileScreen(
                    screen.courierId,
                    courierRepository
                )
                is Screen.TaskDetails -> TaskDetailsScreen(
                    taskId= screen.taskId, taskRequestsRepository = taskRequestRepository
                )
            }
        }
     }
}


@Composable
private fun DrawerButton(
    icon: VectorAsset,
    label: String,
    isSelected: Boolean,
    action: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colors
    val imageAlpha = if (isSelected) {
        1f
    } else {
        0.6f
    }
    val textIconColor = if (isSelected) {
        colors.primary
    } else {
        colors.onSurface.copy(alpha = 0.6f)
    }
    val backgroundColor = if (isSelected) {
        colors.primary.copy(alpha = 0.12f)
    } else {
        colors.surface
    }

    val surfaceModifier = modifier
        .padding(start = 8.dp, top = 8.dp, end = 8.dp)
        .fillMaxWidth()
    Surface(
        modifier = surfaceModifier,
        color = backgroundColor,
        shape = MaterialTheme.shapes.small
    ) {
        TextButton(
            onClick = action,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalGravity = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()) {
                Image(
                    asset = icon,
                    colorFilter = ColorFilter.tint(textIconColor),
                    alpha = imageAlpha
                )
                Spacer(Modifier.preferredWidth(16.dp))
                Text(
                    text = label,
                    style = MaterialTheme.typography.body2,
                    color = textIconColor,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun AppDrawer(
    currentScreen: Screen,
    closeDrawer: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(Modifier.preferredHeight(24.dp))
        CityCourierLogo(Modifier.padding(2.dp).plus(Modifier.height(30.dp).plus(Modifier.width(40.dp))))
        Divider(color = MaterialTheme.colors.onSurface.copy(alpha = .2f))
        DrawerButton(
            icon = Icons.Filled.Home,
            label = "Home",
            isSelected = currentScreen == Screen.WelcomeScreen,
            action = {
                navigateTo(Screen.WelcomeScreen)
                closeDrawer()
            }
        )

        DrawerButton(
            icon = Icons.Filled.ListAlt,
            label = "My Tasks",
            isSelected = currentScreen == Screen.OrdersScreen,
            action = {
                navigateTo(Screen.OrdersScreen)
                closeDrawer()
            }
        )

        DrawerButton(
            icon = Icons.Filled.ListAlt,
            label = "Profile",
            isSelected = currentScreen == Screen.TeamScreen,
            action = {
                navigateTo(Screen.TeamScreen)
                closeDrawer()
            }
        )
    }
}

@Preview("Drawer contents")
@Composable
fun PreviewCityCourierApp() {
    ThemedPreview {
        AppDrawer(
            currentScreen = CityCourierStatus.currentScreen,
            closeDrawer = { }
        )
    }
}

@Preview("Drawer contents dark theme")
@Composable
fun PreviewCityCourierAppDark() {
    ThemedPreview(darkTheme = true) {
        AppDrawer(
            currentScreen = CityCourierStatus.currentScreen,
            closeDrawer = { }
        )
    }
}