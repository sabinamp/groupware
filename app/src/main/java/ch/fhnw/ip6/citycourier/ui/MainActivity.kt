package ch.fhnw.ip6.citycourier.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.setContent

import androidx.ui.material.MaterialTheme
import androidx.ui.material.surface.Surface
import ch.fhnw.ip6.citycourier.ui.orders.OrdersScreen
import ch.fhnw.ip6.citycourier.ui.themes.LightThemeColors
import ch.fhnw.ip6.citycourier.ui.welcome.WelcomeScreen



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApp {
                WelcomeScreen()
            }
        }

    }
}
@Composable
fun MyApp(children: @Composable() () -> Unit) {
    MaterialTheme {
        Surface(color = LightThemeColors.background) {
            children()
        }
    }
}
