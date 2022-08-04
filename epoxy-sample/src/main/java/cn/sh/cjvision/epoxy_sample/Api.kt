package cn.sh.cjvision.epoxy_sample

import cn.sh.cjvision.epoxy_sample.entity.ArticleTop
import cn.sh.cjvision.epoxy_sample.entity.HotKeyEntity
import cn.sh.cjvision.lib_common.BaseEntity
import retrofit2.http.GET

interface Api {

    @GET("article/top/json")
    suspend fun articleTop(): BaseEntity<List<ArticleTop>>
}