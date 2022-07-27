package cn.sh.cjvision.lib_common

data class BaseEntity<T>(var errorCode: Int = 0, var errorMsg: String = "", var data: T? = null)