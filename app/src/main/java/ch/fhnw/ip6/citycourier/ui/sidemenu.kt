package ch.fhnw.ip6.citycourier.ui

import androidx.compose.Composable
import androidx.ui.core.*
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.shape.corner.CircleShape
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.Button
import androidx.ui.material.ButtonStyle

import androidx.ui.material.surface.Surface
import androidx.ui.tooling.preview.Preview
import ch.fhnw.ip6.citycourier.ui.orders.OrdersScreen
import ch.fhnw.ip6.citycourier.ui.themes.LightThemeColors
import ch.fhnw.ip6.citycourier.ui.themes.themeTypography


@Composable
fun MenuItem(text: String, onClick: () -> Unit) {
    Clickable(onClick = onClick ) {
        Text(text = text, style = themeTypography.subtitle1)

    }
}

@Composable
fun SidePopUp(){
    DropdownPopup(dropDownAlignment = DropDownAlignment.Right)
    {

       /* Surface(
            shape = RoundedCornerShape(4.dp),
            elevation = 16.dp,
            color = Color.White,
            modifier=Modifier.None
        )
        {*/
            Column(modifier = Spacing(10.dp)) {

                MenuItem(text ="Edit", onClick = {})
                MenuItem(text = "Delete", onClick = {})
                MenuItem(text = "Details", onClick = {})

                Button(text = "OK",
                        style = ButtonStyle(Color(151, 255, 177),
                            shape = CircleShape
                        ),
                        modifier = MaxHeight(40.dp),
                        onClick = {})
                    HeightSpacer(3.dp)
                    /* FloatingActionButton(
                         color = LightThemeColors.onError,
                         text = "NO",
                         onClick = { *//* do something here *//* })*/
                Button(text = "NO",
                        style = ButtonStyle(LightThemeColors.onError, shape = CircleShape),
                        modifier = MaxHeight(40.dp),
                        onClick = {})

            }
        }

}

@Preview
@Composable
fun SidePopUpPreview() {
    SidePopUp()
}