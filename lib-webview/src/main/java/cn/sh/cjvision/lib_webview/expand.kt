package cn.sh.cjvision.lib_webview

import android.os.Build
import android.util.Log
import android.view.View
import android.view.ViewParent
import java.lang.reflect.Method

/**
 * 让 activity transition 动画过程中可以正常渲染页面
 */


fun View.drawDuringWindowsAnimating() {
    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M
        || Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1
    ) {
        //小于 4.3 和大于 6.0 时不存在此问题，无须处理
        return
    }
    try {
        val rootParent:ViewParent = rootView.parent
        val method:Method = rootParent.javaClass
            .getDeclaredMethod("setDrawDuringWindowsAnimating",Boolean::class.javaPrimitiveType)
        method.isAccessible = true
        method.invoke(rootParent,true)
    }catch (e:Exception){
        e.printStackTrace()
    }
}

fun logD(TAG:String?="logD",msg:String)=Log.d(TAG,msg)
fun logE(TAG:String?="logE",msg:String)=Log.e(TAG,msg)