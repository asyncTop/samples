package cn.sh.cjvision.lib_webview

import android.app.Application
import android.content.Context
import android.content.MutableContextWrapper
import android.os.Looper
import android.webkit.WebView
import java.util.*


object WebViewCacheHolder {

    private val webViewCacheStack = Stack<CJWebView>()

    private const val CACHED_WEB_VIEW_MAX_NUM = 4

    private lateinit var app:Application

    fun init(app:Application) {
        this.app = app
        prepareWebView()
    }

    //预加载
    fun prepareWebView() {
        if (webViewCacheStack.size < CACHED_WEB_VIEW_MAX_NUM) {
            Looper.myQueue().addIdleHandler {
//                log("WebViewCacheStack Size: " + webViewCacheStack.size)
                if (webViewCacheStack.size < CACHED_WEB_VIEW_MAX_NUM) {
                    webViewCacheStack.push(createWebView(MutableContextWrapper(app)))
                }
                false
            }
        }
    }
    //获取webview
    fun acquireWebViewInternal(context: Context): CJWebView {
        if (webViewCacheStack.isEmpty()) {
            return createWebView(context)
        }
        val webView = webViewCacheStack.pop()
        val contextWrapper = webView.context as MutableContextWrapper
        contextWrapper.baseContext = context
        return webView
    }

    //创建webview
    private fun createWebView(context: Context): CJWebView {
        return CJWebView(context)
    }

}