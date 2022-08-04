package cn.sh.cjvision.lib_webview

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.WebSettings
import android.webkit.WebView

@SuppressLint("SetJavaScriptEnabled")
class CJWebView : WebView {

    private val TAG = javaClass.simpleName

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )


    init {
        settings.apply {
            //使用概览模式加载
            loadWithOverviewMode = true
            useWideViewPort = true
            setSupportZoom(false)

            setRenderPriority(WebSettings.RenderPriority.HIGH)
            allowFileAccess = true
            javaScriptCanOpenWindowsAutomatically = true
            domStorageEnabled = true
            setAppCacheEnabled(true)
            builtInZoomControls = true
            allowContentAccess = true
            databaseEnabled = true
            domStorageEnabled = true
            javaScriptEnabled = true
            loadsImagesAutomatically = true
            setSupportMultipleWindows(false)
            cacheMode = WebSettings.LOAD_NO_CACHE

            textZoom = 100
            CookieManager.getInstance()
                .setAcceptThirdPartyCookies(this@CJWebView, true) // 设置允许接受第三方cookie
            mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }

        overScrollMode = View.OVER_SCROLL_ALWAYS

        requestFocus()
        setWebContentsDebuggingEnabled(true) // 设置允许在发布包内打开X5的调试模式

    }

    /**
     *
     * @param func [@kotlin.ExtensionFunctionType] Function1<CJWebView, Unit>
     * @return CJWebView
     */
    inline fun show(func: CJWebView.() -> Unit): CJWebView = apply {
        this.func()
//        this.show()
    }



    /**
     * 业务参数初始化
     */
    fun doInit() {
        val startTime = System.currentTimeMillis()
//        webChromeClient = mWebChromeClient  // 自定义 WebChromeClient
//        webViewClient = mWebClient // 自定义 mWebClient

//        addJavascriptInterface(innerJavascriptInterface, BRIDGE_NAME)
//        addJavascriptInterface(true, API_FLAG)
        clearHistory()
//        enableOfflinePackage = true
        Log.d(TAG,"doInit cast:${System.currentTimeMillis() - startTime}")
    }
    fun release() {
//        loadEmpty()
//        javaScriptNamespaceInterfaces.clear()
//        removeJavascriptInterface(API_FLAG)
//        removeJavascriptInterface(BRIDGE_NAME)
        webChromeClient = null
//        webViewClient = null
//        onLoadListener = null
//        callInfoList?.clear()
        clearCache(false)
        clearHistory()
        if (parent != null) {
            (parent as ViewGroup).removeView(this)
        }
    }

    override fun destroy() {
        Log.d(TAG,"destroy")
        release()
        super.destroy()
    }

}