package ch.fhnw.ip6.citycourier.ui.util

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.layout.width

import androidx.ui.material.AlertDialog
import androidx.ui.material.AlertDialogButtonLayout
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import ch.fhnw.ip6.citycourier.ui.btn.NoButton
import ch.fhnw.ip6.citycourier.ui.btn.OKButton



@Composable
fun ConfirmPopUp(msg: String) {
    AlertDialog(onCloseRequest = {},
        text = { Text("New Task Request") },
        title = { Text(msg) },
        buttonLayout = AlertDialogButtonLayout.SideBySide,
        confirmButton = {
            OKButton(onClick = {
                //todo
            },modifier = Modifier.width(60.dp))
        }, dismissButton = {
            NoButton(onClick = {
                //todo
            })
        })
}

@Composable
fun DisplayAlert(msg: String){
    ConfirmPopUp(msg)
}

@Preview("DisplayAlert")
@Composable
fun DisplayAlertPreview(){
    ConfirmPopUp(msg = "You received a New Task Request")
}
