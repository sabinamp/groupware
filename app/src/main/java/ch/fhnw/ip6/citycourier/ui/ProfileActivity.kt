package ch.fhnw.ip6.citycourier.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.setContent

import androidx.ui.material.MaterialTheme
import androidx.ui.material.surface.Surface
import ch.fhnw.ip6.citycourier.ui.profile.ProfileScreen
import ch.fhnw.ip6.citycourier.ui.themes.LightThemeColors


class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ProfileOverview {
                ProfileScreen("C100")
            }
        }

    }
}
@Composable
fun ProfileOverview(children: @Composable() () -> Unit) {
    MaterialTheme {
        Surface(color = LightThemeColors.background) {
            children()
        }
    }
}
