import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() = application {
    val state = rememberWindowState(
        size = DpSize(375.dp, 700.dp),
        position = WindowPosition(300.dp, 100.dp)
    )
    Window(
        title = "Local Time App",
        onCloseRequest = ::exitApplication,
        state = state
    ) {
        App()
    }
}

@Preview
@Composable
fun AppDesktopPreview() {
    App()
}