package ch.fhnw.ip6.citycourier.ui.welcome

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.material.AlertDialog
import androidx.ui.material.Button
import androidx.ui.material.ButtonStyle
import ch.fhnw.ip6.citycourier.ui.themes.LightThemeColors

@Composable
fun DisplayAlert(msg: String) {
    AlertDialog(onCloseRequest = {},
        text = { Text("New task request") },
        title = { Text("Clicked on ${msg}") },
        //confirmButton = { Text("Accept")},
        confirmButton= {
            Button(text = "Accept",
                style = ButtonStyle(
                    LightThemeColors.primaryVariant,
                    shape = RoundedCornerShape(12.dp)
                ),
                onClick = {})
        }, dismissButton = {
            Button(text = "Reject",
                style = ButtonStyle(
                    LightThemeColors.primaryVariant,
                    shape = RoundedCornerShape(12.dp)
                ),
                onClick = {})
        })
}

