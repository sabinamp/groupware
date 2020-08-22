package ch.fhnw.ip6.citycourier.ui.util

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.layout.padding
import androidx.ui.material.Divider
import androidx.ui.unit.dp
import ch.fhnw.ip6.citycourier.ui.themes.LightThemeColors

@Composable
fun ScreenDivider() {
    Divider(
        modifier = Modifier.padding(horizontal = 14.dp),
        color = LightThemeColors.onSurface.copy(alpha = 0.08f)
    )
}
