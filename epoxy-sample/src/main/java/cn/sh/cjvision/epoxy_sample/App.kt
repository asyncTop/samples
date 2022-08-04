package cn.sh.cjvision.epoxy_sample

import android.app.Application
import com.tencent.bugly.crashreport.CrashReport
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        CrashReport.initCrashReport(applicationContext, "7d76c513f0", false)
    }
}