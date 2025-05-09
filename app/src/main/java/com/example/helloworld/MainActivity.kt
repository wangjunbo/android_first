package com.example.helloworld

import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.activity.compose.BackHandler
import androidx.core.view.WindowCompat
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false) // 允许内容延伸到状态栏
        setContent {
            WebViewScreen()
        }
    }
}

@Composable
fun WebViewScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        // 顶部应用栏
        TopAppBar(
            title = { Text("你的应用名称") }, // 替换为你的应用名
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        )

        // WebView 部分
        val context = LocalContext.current
        val webView = remember {
            WebView(context).apply {
                setupWebView()
                loadUrl("https://t.ddz.cool/?room=wang1991")
            }
        }

        AndroidView(
            factory = { webView },
            modifier = Modifier.weight(1f) // 占据剩余空间
        )
    }
}

private fun WebView.setupWebView() {
    settings.apply {
        javaScriptEnabled = true
        domStorageEnabled = true
        allowFileAccess = true
        mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
    }

    webViewClient = object : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            view?.loadUrl(url ?: "")
            return true
        }
    }
}