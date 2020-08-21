package ch.fhnw.ip6.citycourier.ui

import androidx.compose.frames.ModelList
import androidx.compose.getValue
import androidx.compose.mutableStateOf
import androidx.compose.setValue
import ch.fhnw.ip6.citycourier.model.TaskRequest

/**
 * Class defining the screens we have in the app: welcome, profile, team details and orders
 */
sealed class Screen {
    object WelcomeScreen : Screen()
    object ProfileScreen : Screen()
    object TasksScreen : Screen()

    data class TaskDetails(val taskId: String): Screen()
}

object CityCourierStatus {
    var currentScreen by mutableStateOf<Screen>(Screen.WelcomeScreen)
    val favorites = ModelList<String>()

}

/**
 * Temporary solution pending navigation support.
 */
fun navigateTo(destination: Screen) {
    CityCourierStatus.currentScreen = destination
}