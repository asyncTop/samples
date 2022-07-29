package cn.sh.cjvision.lib_common

import android.app.Activity
import android.util.Log
import androidx.annotation.MainThread
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

@MainThread
inline fun <reified BD : ViewDataBinding> Activity.binding(resId: Int): Lazy<BD> {
    return lazy { DataBindingUtil.setContentView(this, resId) }
}

fun log(msg: String) {
    Log.d("LOG", msg)
}