package sa.edu.iau.cshj.lab07.ui

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun CampusWebPage(url: String, modifier: Modifier = Modifier) {
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                webViewClient = WebViewClient()
                loadUrl(url)
            }
        },
        onRelease = { view -> view.destroy() },
        modifier = modifier.fillMaxSize()
    )
}

@Composable
fun CampusNewsScreen(
    url: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        Button(onClick = onBackClick) { Text("Back") }
        CampusWebPage(url = url, modifier = Modifier.fillMaxWidth().weight(1f))
    }
}

@Composable
fun CampusNewsRoute(onBackClick: () -> Unit, modifier: Modifier = Modifier) {
    CampusNewsScreen(
        url = "https://www.iau.edu.sa",
        onBackClick = onBackClick,
        modifier = modifier
    )
}
