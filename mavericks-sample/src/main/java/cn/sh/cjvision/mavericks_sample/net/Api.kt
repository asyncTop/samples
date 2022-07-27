package cn.sh.cjvision.mavericks_sample.net

import cn.sh.cjvision.lib_common.BaseEntity
import cn.sh.cjvision.mavericks_sample.HotKeyEntity
import retrofit2.http.GET

interface Api {

    @GET("hotkey/json")
    suspend fun getHotKey(): BaseEntity<List<HotKeyEntity>>
}