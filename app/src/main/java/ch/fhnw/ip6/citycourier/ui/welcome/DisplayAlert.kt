package ch.fhnw.ip6.citycourier.ui.welcome

import androidx.compose.Composable
import androidx.ui.foundation.Text

import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.material.AlertDialog
import androidx.ui.material.Button
import androidx.ui.unit.dp

import ch.fhnw.ip6.citycourier.ui.themes.LightThemeColors

@Composable
fun DisplayAlert(msg: String) {
    AlertDialog(onCloseRequest = {},
        text = { Text("New task request") },
        title = { Text("Clicked on ${msg}") },
        //confirmButton = { Text("Accept")},
        confirmButton= {
            Button(
                   backgroundColor =  LightThemeColors.primaryVariant,
                contentColor = LightThemeColors.surface,
                    shape = RoundedCornerShape(12.dp),
                onClick = {}){
                "Accept"
            }
        }, dismissButton = {
            Button(
                    backgroundColor = LightThemeColors.primaryVariant,
                contentColor = LightThemeColors.surface,
                    shape = RoundedCornerShape(12.dp),
                onClick = {}){
                "Reject"
            }
        })
}

