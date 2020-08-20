package ch.fhnw.ip6.citycourier.ui.orders

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.foundation.clickable
import androidx.ui.layout.padding
import androidx.ui.material.Divider
import androidx.ui.material.EmphasisAmbient
import androidx.ui.material.MaterialTheme
import androidx.ui.material.ProvideEmphasis
import androidx.ui.unit.dp
import ch.fhnw.ip6.citycourier.data.TaskRequestsRepository
import ch.fhnw.ip6.citycourier.model.TaskRequest
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
