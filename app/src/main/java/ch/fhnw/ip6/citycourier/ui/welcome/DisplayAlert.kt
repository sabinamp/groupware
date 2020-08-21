package ch.fhnw.ip6.citycourier.ui.welcome

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.layout.preferredSize

import androidx.ui.material.AlertDialog
import androidx.ui.unit.dp
import ch.fhnw.ip6.citycourier.ui.btn.NoButton
import ch.fhnw.ip6.citycourier.ui.btn.OKButton



@Composable
fun DisplayAlert(msg: String) {
    AlertDialog(onCloseRequest = {},
        text = { Text("New task request") },
        title = { Text("Clicked on ${msg}") },

        confirmButton = {
            OKButton(modifier = Modifier.preferredSize(40.dp,40.dp))
        }, dismissButton = {
            NoButton()
        })
}

