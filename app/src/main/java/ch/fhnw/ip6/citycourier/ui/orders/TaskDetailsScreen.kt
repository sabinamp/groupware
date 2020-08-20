package ch.fhnw.ip6.citycourier.ui.orders

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.foundation.clickable
import androidx.ui.foundation.contentColor
import androidx.ui.layout.padding
import androidx.ui.material.*
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.ArrowBack
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import ch.fhnw.ip6.citycourier.data.TaskRequestsRepository
import ch.fhnw.ip6.citycourier.model.TaskRequest
import ch.fhnw.ip6.citycourier.ui.Screen
import ch.fhnw.ip6.citycourier.ui.ThemedPreview
import ch.fhnw.ip6.citycourier.ui.navigateTo
import ch.fhnw.ip6.citycourier.ui.themes.LightThemeColors
import ch.fhnw.ip6.citycourier.ui.themes.themeTypography

class TaskDetailsScreen(taskId: String, taskRequestsRepository: TaskRequestsRepository) {
    @Composable
    private fun TopSection(task: TaskRequest) {
        ProvideEmphasis(EmphasisAmbient.current.high) {
            Text(
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp),
                text = "This task has been assigned to you",
                style = themeTypography.subtitle1
            )
        }

        ScreenDivider()
    }

    @Composable
    private fun ScreenDivider() {
        Divider(
            modifier = Modifier.padding(horizontal = 14.dp),
            color = LightThemeColors.onSurface.copy(alpha = 0.08f)
        )
    }
}
@Composable
fun ScreenTopBar(){
    TopAppBar(
        title = {
            Text(
                text = "City Courier. Task Details",
                style = themeTypography.h3,
                color = contentColor()
            )
        },
        navigationIcon = {
            IconButton(onClick = { navigateTo(Screen.WelcomeScreen) }) {
                Icon(Icons.Filled.ArrowBack)
            }
        }
    )

}
@Preview("ScreenTopBar")
@Composable
fun AppBarPreview(){
    ThemedPreview {
        ScreenTopBar()
    }
}