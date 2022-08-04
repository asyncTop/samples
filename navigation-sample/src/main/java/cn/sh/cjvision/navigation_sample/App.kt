package cn.sh.cjvision.navigation_sample

import android.app.Application
import cn.sh.cjvision.lib_webview.WebViewCacheHolder
import com.airbnb.mvrx.Mavericks
import com.tencent.bugly.crashreport.CrashReport

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        CrashReport.initCrashReport(applicationContext, "7d76c513f0", false)
        Mavericks.initialize(this)
        WebViewCacheHolder.init(this)
    }
}