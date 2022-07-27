package cn.sh.cjvision.mavericks_sample

import android.app.Application
import com.airbnb.mvrx.Mavericks
import com.drake.brv.utils.BRV
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    init {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { _, _ -> ClassicsHeader(this) }
        SmartRefreshLayout.setDefaultRefreshFooterCreator { _, _ -> ClassicsFooter(this) }
    }
    override fun onCreate() {
        super.onCreate()
        Mavericks.initialize(this)
        BRV.modelId = BR.data
    }
}