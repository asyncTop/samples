package cn.sh.cjvision.lib_common

import android.app.Activity
import android.util.Log
import androidx.annotation.MainThread
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

@MainThread
inline fun <reified BD : ViewDataBinding> Activity.binding(resId: Int): Lazy<BD> {
    return lazy { DataBindingUtil.setContentView(this, resId) }
}

//@MainThread
//inline fun <reified BD :ViewDataBinding> Fragment.binding(resId: Int):Lazy<BD>{
//    onCreateView()
//}

fun log(msg: String) {
    Log.d("LOG", msg)
}