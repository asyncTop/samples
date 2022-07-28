package cn.sh.cjvision.epoxy_sample.net

import cn.sh.cjvision.epoxy_sample.databinding.ArticleEntity
import cn.sh.cjvision.lib_common.BaseEntity
import retrofit2.http.GET

interface Api {
    @GET("article/list/0/json")
    suspend fun getArticle(): BaseEntity<ArticleEntity>
}