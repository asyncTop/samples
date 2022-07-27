package cn.sh.cjvision.mavericks_sample

import cn.sh.cjvision.mavericks_sample.net.Api
import javax.inject.Inject


class DataRepository @Inject constructor(private val api: Api) {
    suspend fun getHotKey() = api.getHotKey()
}